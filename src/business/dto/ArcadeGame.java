package business.dto;

import java.util.Date;

public class ArcadeGame {
    private int id;
    private String gameName;
    private String developerName;
    private Date releaseDate;
    private Date lastMaintenanceWindow;
    
    // Additional fields for statistics
    private Integer totalPlays;
    private Integer highScore;
    private Double averageScore;
    
    // Constructor for basic game information
    public ArcadeGame(int id, String gameName, String developerName, Date releaseDate) {
        this.id = id;
        this.gameName = gameName;
        this.developerName = developerName;
        this.releaseDate = releaseDate;
    }
    
    // Constructor with maintenance window
    public ArcadeGame(int id, String gameName, String developerName, Date releaseDate, Date lastMaintenanceWindow) {
        this(id, gameName, developerName, releaseDate);
        this.lastMaintenanceWindow = lastMaintenanceWindow;
    }
    
    // Constructor for game statistics
    public ArcadeGame(int id, String gameName, String developerName, Date releaseDate, 
                    Integer totalPlays, Integer highScore, Double averageScore) {
        this(id, gameName, developerName, releaseDate);
        this.totalPlays = totalPlays;
        this.highScore = highScore;
        this.averageScore = averageScore;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getGameName() {
        return gameName;
    }
    
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    
    public String getDeveloperName() {
        return developerName;
    }
    
    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }
    
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public Date getLastMaintenanceWindow() {
        return lastMaintenanceWindow;
    }
    
    public void setLastMaintenanceWindow(Date lastMaintenanceWindow) {
        this.lastMaintenanceWindow = lastMaintenanceWindow;
    }
    
    public Integer getTotalPlays() {
        return totalPlays;
    }
    
    public void setTotalPlays(Integer totalPlays) {
        this.totalPlays = totalPlays;
    }
    
    public Integer getHighScore() {
        return highScore;
    }
    
    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }
    
    public Double getAverageScore() {
        return averageScore;
    }
    
    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game ID: ").append(id)
          .append(", Name: ").append(gameName)
          .append(", Developer: ").append(developerName)
          .append(", Released: ").append(releaseDate);
        
        if (lastMaintenanceWindow != null) {
            sb.append(", Last Maintenance: ").append(lastMaintenanceWindow);
        }
        
        if (totalPlays != null) {
            sb.append("\n  Total Plays: ").append(totalPlays)
              .append(", High Score: ").append(highScore)
              .append(", Average Score: ").append(String.format("%.2f", averageScore));
        }
        
        return sb.toString();
    }
} 