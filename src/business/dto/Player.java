package business.dto;

public class Player {
    private int id;
    private String userName;
    private Integer favoriteGameId;
    private String favoriteGameName;
    
    public Player(int id, String userName, Integer favoriteGameId, String favoriteGameName) {
        this.id = id;
        this.userName = userName;
        this.favoriteGameId = favoriteGameId;
        this.favoriteGameName = favoriteGameName;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Integer getFavoriteGameId() {
        return favoriteGameId;
    }
    
    public void setFavoriteGameId(Integer favoriteGameId) {
        this.favoriteGameId = favoriteGameId;
    }
    
    public String getFavoriteGameName() {
        return favoriteGameName;
    }
    
    public void setFavoriteGameName(String favoriteGameName) {
        this.favoriteGameName = favoriteGameName;
    }
    
    @Override
    public String toString() {
        return "Player ID: " + id + 
               ", Username: " + userName + 
               (favoriteGameName != null ? ", Favorite Game: " + favoriteGameName : ", No favorite game");
    }
} 