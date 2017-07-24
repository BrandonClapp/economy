package me.netyeti.other;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ScoreboardObjectiveGold implements Listener {

    private JavaPlugin plugin;
    private String goldLabel = ChatColor.GOLD + "Gold: ";

    //private HashMap<UUID, Scoreboard> scoreboards = new HashMap<UUID, Scoreboard>();
    private HashMap<UUID, Integer> goldGenerationTaskIds = new HashMap<>();
    private ArrayList<PlayerScoreboardGoldContext> scoreboards = new ArrayList<>();


    private BukkitScheduler _scheduler;
    private String goldObjName = "gold";

    public ScoreboardObjectiveGold(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        registerPlayerBoard(player);
        addOneGoldPerSecond(player);

        Scoreboard playersBoard = this.scoreboards.stream()
                .filter(scb -> scb.getPlayerUUID().equals(player.getUniqueId()))
                .findFirst()
                .get()
                .getPlayerScoreboard();

        // Scoreboard playersBoard = this.scoreboards.get(player.getUniqueId());
        player.setScoreboard(playersBoard);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        // when player leaves, be sure to stop giving them gold.
        // _scheduler.cancelTask(_taskId);

        int taskId = this.goldGenerationTaskIds.get(player.getUniqueId());
        _scheduler.cancelTask(taskId);

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = (Player)event.getEntity();

        if(!(player.getKiller() instanceof Player)) return;

        Player killer = player.getKiller();

        addGold(killer, 300);
        killer.sendMessage(ChatColor.GOLD + "+300 gold!");
    }


    private void addGold(Player player, int amount) {
        Scoreboard board = scoreboards.stream()
                .filter(sb -> sb.getPlayerUUID().equals(player.getUniqueId()))
                .findFirst()
                .get()
                .getPlayerScoreboard();

        Score score = board.getObjective(goldObjName).getScore(goldLabel);
        int newGold = score.getScore() + 300;
        score.setScore(newGold);
    }


    public void addOneGoldPerSecond(Player p) {
        this._scheduler = this.plugin.getServer().getScheduler();

        int taskId = _scheduler.scheduleSyncRepeatingTask(this.plugin, () -> {
            PlayerScoreboardGoldContext playerContext = scoreboards.stream()
                    .filter((sbc) -> sbc.getPlayerUUID().equals(p.getUniqueId()))
                    .findFirst()
                    .get();

            Scoreboard board = playerContext.getPlayerScoreboard();
            Objective obj = board.getObjective(goldObjName);

            Score currentScore = obj.getScore(this.goldLabel);
            int currentScoreValue = currentScore.getScore();
            int newScoreValue = currentScoreValue + 1;

            currentScore.setScore(newScoreValue);

            playerContext.setPlayerScoreboard(board);

            //p.sendMessage("Scheduler is updating... currentGold: " + currentScoreValue + " new score: " + newScoreValue);

        }, 0L, 20L);

        this.goldGenerationTaskIds.put(p.getUniqueId(), taskId);
    }

    private boolean registerPlayerBoard(Player player) {
        //boolean isNewPlayer = this.scoreboards.get(player.getUniqueId()) == null;

        // player is new if we don't currently have a scoreboard for them.
        boolean playerExists = this.scoreboards.stream()
                .anyMatch(p -> p.getPlayerUUID().equals(player.getUniqueId()));

        if(!playerExists) {
            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective(goldObjName, "dummy");

            Score score = obj.getScore(this.goldLabel);
            score.setScore(1000); // start with 1000 gold.

            obj.setDisplayName(ChatColor.DARK_AQUA + "Netyeti Server");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            //this.scoreboards.put(player.getUniqueId(), board);
            scoreboards.add(new PlayerScoreboardGoldContext(player.getUniqueId(), board));
        }

        return playerExists;
    }

}
