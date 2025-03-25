package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DataMgr provides centralized database connection management for all databases
 * in the application.
 */
public class DataMgr {
    private static DataMgr instance;
    private static final Logger LOGGER = Logger.getLogger(DataMgr.class.getName());

    // Database connection parameters
    private final String MEAL_PLANNING_URL = "jdbc:mysql://localhost:3306/MealPlanning";
    private final String ARCADE_GAMES_URL = "jdbc:mysql://localhost:3306/ArcadeGames";
    private final String VIDEO_GAME_SYSTEMS_URL = "jdbc:mysql://localhost:3306/VideoGameSystems";
    private String username;
    private String password;

    // Private constructor for singleton pattern
    private DataMgr() {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "JDBC Driver not found", e);
        }
    }

    /**
     * Get the singleton instance of DataMgr
     */
    public static synchronized DataMgr getInstance() {
        if (instance == null) {
            instance = new DataMgr();
        }
        return instance;
    }

    /**
     * Set credentials for database connections
     */
    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get a connection to the MealPlanning database
     */
    public Connection getMealPlanningConnection() throws SQLException {
        LOGGER.info("Connecting to MealPlanning database");
        return DriverManager.getConnection(MEAL_PLANNING_URL, username, password);
    }

    /**
     * Get a connection to the ArcadeGames database
     */
    public Connection getArcadeGamesConnection() throws SQLException {
        LOGGER.info("Connecting to ArcadeGames database");
        return DriverManager.getConnection(ARCADE_GAMES_URL, username, password);
    }

    /**
     * Get a connection to the VideoGameSystems database
     */
    public Connection getVideoGameSystemsConnection() throws SQLException {
        LOGGER.info("Connecting to VideoGameSystems database");
        return DriverManager.getConnection(VIDEO_GAME_SYSTEMS_URL, username, password);
    }

    /**
     * Close a connection safely
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.info("Database connection closed");
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing connection", e);
            }
        }
    }
}