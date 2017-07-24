package me.netyeti.other;

import org.bukkit.scoreboard.Scoreboard;

import java.util.UUID;

public class PlayerScoreboardGoldContext {

    public PlayerScoreboardGoldContext(UUID playerId, Scoreboard board) {
        this.playerUUID = playerId;
        this.playerScoreboard = board;
    }

    private UUID playerUUID;

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public void setPlayerUUID(UUID value) {
        this.playerUUID = value;
    }

    private Scoreboard playerScoreboard;

    public Scoreboard getPlayerScoreboard() {
        return playerScoreboard;
    }

    public void setPlayerScoreboard(Scoreboard playerScoreboard) {
        this.playerScoreboard = playerScoreboard;
    }

//    private int goldIncrementTaskId;
//
//    public int getGoldIncrementTaskId() {
//        return goldIncrementTaskId;
//    }
//
//    public void setGoldIncrementTaskId(int goldIncrementTaskId) {
//        this.goldIncrementTaskId = goldIncrementTaskId;
//    }
}
