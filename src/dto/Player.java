package dto;

public class Player {
    private Integer id;
    private String userName;
    private Integer favoriteGame;
    private String favoriteGameName;

    public Player() {
    }

    public Player(Integer id, String userName, Integer favoriteGame) {
        this.id = id;
        this.userName = userName;
        this.favoriteGame = favoriteGame;
    }

    public Player(Integer id, String userName, Integer favoriteGame, String favoriteGameName) {
        this.id = id;
        this.userName = userName;
        this.favoriteGame = favoriteGame;
        this.favoriteGameName = favoriteGameName;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getFavoriteGame() {
        return favoriteGame;
    }

    public void setFavoriteGame(Integer favoriteGame) {
        this.favoriteGame = favoriteGame;
    }

    public String getFavoriteGameName() {
        return favoriteGameName;
    }

    public void setFavoriteGameName(String favoriteGameName) {
        this.favoriteGameName = favoriteGameName;
    }

    @Override
    public String toString() {
        return "Player: " + userName +
                "\nID: " + id +
                "\nFavorite Game ID: " + favoriteGame +
                (favoriteGameName != null ? "\nFavorite Game: " + favoriteGameName : "");
    }
}