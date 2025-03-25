package business.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.dto.ArcadeGame;
import business.dto.Player;
import business.dto.Score;
import dal.ArcadeGamesDAL;

/**
 * Business logic layer for ArcadeGames
 */
public class ArcadeGamesService {
    private static final Logger LOGGER = Logger.getLogger(ArcadeGamesService.class.getName());
    
    private final ArcadeGamesDAL arcadeGamesDAL;
    
    public ArcadeGamesService(String user, String password) {
        this.arcadeGamesDAL = new ArcadeGamesDAL(user, password);
    }
    
    /**
     * Get top scores across all games
     */
    public List<Score> getTopScores(int limit) {
        LOGGER.log(Level.INFO, "Fetching top " + limit + " scores");
        return arcadeGamesDAL.getTopScores(limit);
    }
    
    /**
     * Get scores for a specific player
     */
    public List<Score> getPlayerScores(int playerId) {
        LOGGER.log(Level.INFO, "Fetching scores for player ID: " + playerId);
        return arcadeGamesDAL.getPlayerScores(playerId);
    }
    
    /**
     * Get game statistics
     */
    public List<ArcadeGame> getGameStatistics() {
        LOGGER.log(Level.INFO, "Fetching game statistics");
        return arcadeGamesDAL.getGameStatistics();
    }
    
    /**
     * Get all players
     */
    public List<Player> getAllPlayers() {
        LOGGER.log(Level.INFO, "Fetching all players");
        return arcadeGamesDAL.getAllPlayers();
    }
} 