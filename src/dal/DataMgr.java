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
    private static final Logger LOGGER = Logger.getLogger(DataMgr.class.getName());
    
    // Connection strings and parameters
    private static final String JDBC_URL_PREFIX = "jdbc:mysql://localhost:3306/";
    
    // Database names
    private static final String MEAL_PLANNING_DB = "MealPlanning";
    private static final String ARCADE_GAMES_DB = "ArcadeGames";
    private static final String VIDEO_GAME_SYSTEMS_DB = "VideoGameSystems";
    
    // Singleton instance
    private static DataMgr instance;
    
    // Private constructor to enforce singleton pattern
    private DataMgr() {
        // Try to load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found", e);
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
     * Get a connection to the MealPlanning database
     */
    public Connection getMealPlanningConnection(String user, String password) throws SQLException {
        return getConnection(MEAL_PLANNING_DB, user, password);
    }
    
    /**
     * Get a connection to the ArcadeGames database
     */
    public Connection getArcadeGamesConnection(String user, String password) throws SQLException {
        return getConnection(ARCADE_GAMES_DB, user, password);
    }
    
    /**
     * Get a connection to the VideoGameSystems database
     */
    public Connection getVideoGameSystemsConnection(String user, String password) throws SQLException {
        return getConnection(VIDEO_GAME_SYSTEMS_DB, user, password);
    }
    
    /**
     * Generic method to get a connection to any database
     */
    private Connection getConnection(String databaseName, String user, String password) throws SQLException {
        try {
            return DriverManager.getConnection(JDBC_URL_PREFIX + databaseName, user, password);
        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, "Failed to connect to database " + databaseName, exception);
            throw exception;
        }
    }
    
    /**
     * Close a connection safely
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.INFO, "Connection closed successfully");
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing connection", e);
            }
        }
    }
} 