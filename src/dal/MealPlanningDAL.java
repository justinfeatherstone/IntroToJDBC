package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.Ingredient;
import dto.Recipe;
import dal.DataMgr;

/**
 * Data Access Layer for the MealPlanning database
 */
public class MealPlanningDAL {
    private static final Logger LOGGER = Logger.getLogger(MealPlanningDAL.class.getName());

    private final String user;
    private final String password;
    private final DataMgr dataMgr;

    public MealPlanningDAL(String user, String password) {
        this.user = user;
        this.password = password;
        this.dataMgr = DataMgr.getInstance();
    }

    /**
     * Get all recipes from the MealPlanning database
     */
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getMealPlanningConnection();
            statement = connection.createStatement();

            String query = "SELECT r.RecipeName, r.CookbookName, r.TotalServings, " +
                    "c.IsBook, c.Website " +
                    "FROM Recipe r " +
                    "JOIN Cookbook c ON r.CookbookName = c.CookbookName";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Recipe recipe = new Recipe(
                        resultSet.getString("RecipeName"),
                        resultSet.getString("CookbookName"),
                        resultSet.getInt("TotalServings"),
                        resultSet.getBoolean("IsBook"),
                        resultSet.getString("Website"));
                recipes.add(recipe);
            }

            LOGGER.log(Level.INFO, "Retrieved " + recipes.size() + " recipes");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving recipes", e);
        } finally {
            closeResources(resultSet, statement, connection);
        }

        return recipes;
    }

    /**
     * Get ingredients for a specific recipe
     */
    public List<Ingredient> getIngredientsForRecipe(String recipeName) {
        List<Ingredient> ingredients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getMealPlanningConnection();

            String query = "SELECT i.Id, i.IngredientName, i.IngredientType " +
                    "FROM Ingredients i " +
                    "JOIN Meal m ON i.Id = m.IngredientId " +
                    "WHERE m.RecipeName = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, recipeName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient(
                        resultSet.getInt("Id"),
                        resultSet.getString("IngredientName"),
                        resultSet.getString("IngredientType"));
                ingredients.add(ingredient);
            }

            LOGGER.log(Level.INFO, "Retrieved " + ingredients.size() + " ingredients for recipe " + recipeName);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving ingredients for recipe", e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return ingredients;
    }

    /**
     * Execute the GetRecipes stored procedure
     * Assuming the stored procedure exists in the database
     */
    public List<Recipe> getRecipesFromStoredProcedure() {
        List<Recipe> recipes = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            dataMgr.setCredentials(user, password);
            connection = dataMgr.getMealPlanningConnection();

            callableStatement = connection.prepareCall("{CALL GetRecipes()}");
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Recipe recipe = new Recipe(
                        resultSet.getString("RecipeName"),
                        resultSet.getString("CookbookName"),
                        resultSet.getInt("TotalServings"),
                        false, // Default values since the stored procedure might not return these
                        null);
                recipes.add(recipe);
            }

            LOGGER.log(Level.INFO, "Retrieved " + recipes.size() + " recipes from stored procedure");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error executing GetRecipes stored procedure", e);
        } finally {
            closeResources(resultSet, callableStatement, connection);
        }

        return recipes;
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