package presentation;

import dal.ArcadeGamesDAL;
import dal.DataMgr;
import dal.MealPlanningDAL;
import dto.ArcadeGame;
import dto.Ingredient;
import dto.Recipe;
import dto.Score;

import java.util.List;
import java.util.Scanner;

public class MenuSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static MealPlanningDAL mealPlanningDAL;
    private static ArcadeGamesDAL arcadeGamesDAL;

    public static void main(String[] args) {
        System.out.println("====== JDBC Database Application ======");

        // Setup database credentials
        System.out.print("Enter database username: ");
        String username = scanner.nextLine();

        System.out.print("Enter database password: ");
        String password = scanner.nextLine();

        // Initialize data manager with credentials
        DataMgr dataMgr = DataMgr.getInstance();
        dataMgr.setCredentials(username, password);

        // Initialize DAL classes
        mealPlanningDAL = new MealPlanningDAL(username, password);
        arcadeGamesDAL = new ArcadeGamesDAL(username, password);

        // Show main menu
        displayMainMenu();

        // Clean up
        scanner.close();
        System.out.println("Application terminated. Goodbye!");
    }

    /**
     * Displays the main menu and handles user selections
     */
    private static void displayMainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Database Operations Menu =====");
            System.out.println("1. Run a single query against the meal planning database");
            System.out.println("2. Run a single query against the Arcade Games database");
            System.out.println("3. Run GetRecipes stored procedure against the meal planning database");
            System.out.println("4. Run statement method for Arcade Games (getAllGames)");
            System.out.println("5. Run prepared statement method for Arcade Games (getScoresForPlayer)");
            System.out.println("6. Run callable statement method for Arcade Games (getTopScores)");
            System.out.println("0. Exit");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    runMealPlanningQuery();
                    break;
                case 2:
                    runArcadeGamesQuery();
                    break;
                case 3:
                    runMealPlanningStoredProcedure();
                    break;
                case 4:
                    runArcadeGamesStatement();
                    break;
                case 5:
                    runArcadeGamesPreparedStatement();
                    break;
                case 6:
                    runArcadeGamesCallableStatement();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Runs a basic query against the MealPlanning database
     */
    private static void runMealPlanningQuery() {
        System.out.println("\n===== All Recipes =====");
        List<Recipe> recipes = mealPlanningDAL.getAllRecipes();

        if (recipes.isEmpty()) {
            System.out.println("No recipes found or an error occurred.");
        } else {
            for (Recipe recipe : recipes) {
                System.out.println("\n" + recipe);
                System.out.println("------------------------");
            }
            System.out.println("Total recipes: " + recipes.size());
        }
    }

    /**
     * Runs a basic query against the ArcadeGames database
     */
    private static void runArcadeGamesQuery() {
        System.out.println("\n===== All Arcade Games =====");
        List<ArcadeGame> games = arcadeGamesDAL.getAllGames();

        if (games.isEmpty()) {
            System.out.println("No games found or an error occurred.");
        } else {
            for (ArcadeGame game : games) {
                System.out.println("\n" + game);
                System.out.println("------------------------");
            }
            System.out.println("Total games: " + games.size());
        }
    }

    /**
     * Runs the stored procedure from the MealPlanning database
     */
    private static void runMealPlanningStoredProcedure() {
        System.out.println("\n===== Recipes from Stored Procedure =====");
        List<Recipe> recipes = mealPlanningDAL.getRecipesFromStoredProcedure();

        if (recipes.isEmpty()) {
            System.out.println("No recipes found or an error occurred. Make sure the stored procedure exists.");
        } else {
            for (Recipe recipe : recipes) {
                System.out.println("\n" + recipe);
                System.out.println("------------------------");
            }
            System.out.println("Total recipes: " + recipes.size());
        }
    }

    /**
     * Runs the statement method from the ArcadeGames DAL
     */
    private static void runArcadeGamesStatement() {
        System.out.println("\n===== All Arcade Games (using Statement) =====");
        List<ArcadeGame> games = arcadeGamesDAL.getAllGames();

        if (games.isEmpty()) {
            System.out.println("No games found or an error occurred.");
        } else {
            for (ArcadeGame game : games) {
                System.out.println("\n" + game);
                System.out.println("------------------------");
            }
            System.out.println("Total games: " + games.size());
        }
    }

    /**
     * Runs the prepared statement method from the ArcadeGames DAL
     */
    private static void runArcadeGamesPreparedStatement() {
        int playerId = getIntInput("Enter player ID to get scores: ");

        System.out.println("\n===== Scores for Player ID " + playerId + " =====");
        List<Score> scores = arcadeGamesDAL.getPlayerScores(playerId);

        if (scores.isEmpty()) {
            System.out.println("No scores found for this player or an error occurred.");
        } else {
            for (Score score : scores) {
                System.out.println("\n" + score);
                System.out.println("------------------------");
            }
            System.out.println("Total scores: " + scores.size());
        }
    }

    /**
     * Runs the callable statement method from the ArcadeGames DAL
     */
    private static void runArcadeGamesCallableStatement() {
        System.out.println("\n===== Top Scores (using Callable Statement) =====");
        int limit = getIntInput("Enter number of top scores to display: ");
        List<Score> scores = arcadeGamesDAL.getTopScores(limit);

        if (scores.isEmpty()) {
            System.out.println("No scores found or an error occurred. Make sure the stored procedure exists.");
        } else {
            for (Score score : scores) {
                System.out.println("\n" + score);
                System.out.println("------------------------");
            }
            System.out.println("Total scores: " + scores.size());
        }
    }

    /**
     * Helper method to get integer input with validation
     */
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}