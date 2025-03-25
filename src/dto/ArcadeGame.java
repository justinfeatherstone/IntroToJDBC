package dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ArcadeGame {
    private Integer id;
    private String gameName;
    private String developerName;
    private Date releaseDate;
    private Timestamp lastMaintenanceWindow;

    public ArcadeGame() {
    }

    public ArcadeGame(Integer id, String gameName, String developerName,
            Date releaseDate, Timestamp lastMaintenanceWindow) {
        this.id = id;
        this.gameName = gameName;
        this.developerName = developerName;
        this.releaseDate = releaseDate;
        this.lastMaintenanceWindow = lastMaintenanceWindow;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Timestamp getLastMaintenanceWindow() {
        return lastMaintenanceWindow;
    }

    public void setLastMaintenanceWindow(Timestamp lastMaintenanceWindow) {
        this.lastMaintenanceWindow = lastMaintenanceWindow;
    }

    @Override
    public String toString() {
        return "Game: " + gameName +
                "\nDeveloper: " + developerName +
                "\nRelease Date: " + releaseDate +
                "\nLast Maintenance: " + lastMaintenanceWindow;
    }
}