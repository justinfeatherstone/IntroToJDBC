package business.dto;

import java.util.Date;

public class Score {
    private int id;
    private int gameId;
    private int playerId;
    private int score;
    private Date gameDate;
    
    // Additional fields from joins
    private String gameName;
    private String playerName;
    
    public Score(int id, int gameId, int playerId, int score, Date gameDate, 
                String gameName, String playerName) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.score = score;
        this.gameDate = gameDate;
        this.gameName = gameName;
        this.playerName = playerName;
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
    
    public Date getGameDate() {
        return gameDate;
    }
    
    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
    
    public String getGameName() {
        return gameName;
    }
    
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    @Override
    public String toString() {
        return "Score: " + score + 
               " | Game: " + gameName + 
               " | Player: " + playerName + 
               " | Date: " + gameDate;
    }
} 