package dto;

import java.sql.Timestamp;

public class Score {
    private int id;
    private int gameId;
    private int playerId;
    private int score;
    private Timestamp gameDate;
    private String gameName;
    private String userName;

    public Score(int id, int gameId, int playerId, int score, Timestamp gameDate, String gameName, String userName) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.score = score;
        this.gameDate = gameDate;
        this.gameName = gameName;
        this.userName = userName;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getGameDate() {
        return gameDate;
    }

    public void setGameDate(Timestamp gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format("Score [ID: %d, Game: %s, Player: %s, Score: %d, Date: %s]",
                id, gameName, userName, score, gameDate);
    }
}