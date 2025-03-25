package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.ArcadeGame;
import dto.Player;
import dto.Score;
import dal.DataMgr;

/**
 * Data Access Layer for the ArcadeGames database
 */
public class ArcadeGamesDAL {
    private static final Logger LOGGER = Logger.getLogger(ArcadeGamesDAL.class.getName());

    private final String user;
    private final String password;
    private final DataMgr dataMgr;

    public ArcadeGamesDAL(String user, String password) {
        this.user = user;
        this.password = password;
        this.dataMgr = DataMgr.getInstance();
    }

    /**
     * Get top scores across all games using a regular Statement
     * 
     * @return List of Score objects
     */
    public List<Score> getTopScores(int limit) {
        List<Score> topScores = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getArcadeGamesConnection();
            statement = connection.createStatement();

            // Using a regular Statement to execute a query
            String query = "SELECT s.Id, s.GameId, s.PlayerId, s.Score, s.GameDate, " +
                    "g.GameName, p.UserName " +
                    "FROM Score s " +
                    "JOIN Game g ON s.GameId = g.Id " +
                    "JOIN Player p ON s.PlayerId = p.Id " +
                    "ORDER BY s.Score DESC " +
                    "LIMIT " + limit;

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Score score = new Score(
                        resultSet.getInt("Id"),
                        resultSet.getInt("GameId"),
                        resultSet.getInt("PlayerId"),
                        resultSet.getInt("Score"),
                        resultSet.getTimestamp("GameDate"),
                        resultSet.getString("GameName"),
                        resultSet.getString("UserName"));
                topScores.add(score);
            }

            LOGGER.log(Level.INFO, "Retrieved " + topScores.size() + " top scores");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving top scores", e);
        } finally {
            closeResources(resultSet, statement, connection);
        }

        return topScores;
    }

    /**
     * Get scores for a specific player using a PreparedStatement
     * 
     * @param playerId ID of the player
     * @return List of Score objects for the player
     */
    public List<Score> getPlayerScores(int playerId) {
        List<Score> playerScores = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getArcadeGamesConnection();

            // Using a PreparedStatement to avoid SQL injection
            String query = "SELECT s.Id, s.GameId, s.PlayerId, s.Score, s.GameDate, " +
                    "g.GameName, p.UserName " +
                    "FROM Score s " +
                    "JOIN Game g ON s.GameId = g.Id " +
                    "JOIN Player p ON s.PlayerId = p.Id " +
                    "WHERE s.PlayerId = ? " +
                    "ORDER BY s.Score DESC";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, playerId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Score score = new Score(
                        resultSet.getInt("Id"),
                        resultSet.getInt("GameId"),
                        resultSet.getInt("PlayerId"),
                        resultSet.getInt("Score"),
                        resultSet.getTimestamp("GameDate"),
                        resultSet.getString("GameName"),
                        resultSet.getString("UserName"));
                playerScores.add(score);
            }

            LOGGER.log(Level.INFO, "Retrieved " + playerScores.size() + " scores for player ID " + playerId);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving player scores", e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return playerScores;
    }

    /**
     * Get game statistics using a CallableStatement
     * Note: This assumes you've created a stored procedure in the ArcadeGames
     * database
     * called "GetGameStatistics" that returns game statistics
     * 
     * CREATE PROCEDURE GetGameStatistics()
     * BEGIN
     * SELECT g.Id, g.GameName, g.DeveloperName, g.ReleaseDate,
     * COUNT(s.Id) AS TotalPlays,
     * MAX(s.Score) AS HighScore,
     * AVG(s.Score) AS AverageScore
     * FROM Game g
     * LEFT JOIN Score s ON g.Id = s.GameId
     * GROUP BY g.Id, g.GameName, g.DeveloperName, g.ReleaseDate;
     * END
     */
    public List<ArcadeGame> getGameStatistics() {
        List<ArcadeGame> gameStats = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getArcadeGamesConnection();

            // Using a CallableStatement to execute a stored procedure
            callableStatement = connection.prepareCall("{CALL GetGameStatistics()}");

            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                ArcadeGame game = new ArcadeGame(
                        resultSet.getInt("Id"),
                        resultSet.getString("GameName"),
                        resultSet.getString("DeveloperName"),
                        resultSet.getDate("ReleaseDate"),
                        null); // LastMaintenanceWindow is not available in statistics
                gameStats.add(game);
            }

            LOGGER.log(Level.INFO, "Retrieved statistics for " + gameStats.size() + " games");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing stored procedure GetGameStatistics", e);
        } finally {
            closeResources(resultSet, callableStatement, connection);
        }

        return gameStats;
    }

    /**
     * Get all players in the database
     */
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getArcadeGamesConnection();
            statement = connection.createStatement();

            String query = "SELECT p.Id, p.UserName, p.FavoriteGame, g.GameName as FavoriteGameName " +
                    "FROM Player p " +
                    "LEFT JOIN Game g ON p.FavoriteGame = g.Id";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Player player = new Player(
                        resultSet.getInt("Id"),
                        resultSet.getString("UserName"),
                        resultSet.getObject("FavoriteGame") != null ? resultSet.getInt("FavoriteGame") : null,
                        resultSet.getString("FavoriteGameName"));
                players.add(player);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving players", e);
        } finally {
            closeResources(resultSet, statement, connection);
        }

        return players;
    }

    /**
     * Get all games from the database
     */
    public List<ArcadeGame> getAllGames() {
        List<ArcadeGame> games = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getArcadeGamesConnection();
            statement = connection.createStatement();

            String query = "SELECT g.Id, g.GameName, g.DeveloperName, g.ReleaseDate, g.LastMaintenanceWindow " +
                    "FROM Game g";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ArcadeGame game = new ArcadeGame(
                        resultSet.getInt("Id"),
                        resultSet.getString("GameName"),
                        resultSet.getString("DeveloperName"),
                        resultSet.getDate("ReleaseDate"),
                        resultSet.getTimestamp("LastMaintenanceWindow"));
                games.add(game);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving games", e);
        } finally {
            closeResources(resultSet, statement, connection);
        }

        return games;
    }

    /**
     * Helper method to close database resources
     */
    private void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                dataMgr.closeConnection(connection);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error closing resources", e);
        }
    }
}