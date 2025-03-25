package business.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.dto.Recipe;
import business.dto.Ingredient;
import dal.MealPlanningDAL;

/**
 * Business logic layer for MealPlanning
 */
public class MealPlanningService {
    private static final Logger LOGGER = Logger.getLogger(MealPlanningService.class.getName());
    
    private final MealPlanningDAL mealPlanningDAL;
    
    public MealPlanningService(String user, String password) {
        this.mealPlanningDAL = new MealPlanningDAL(user, password);
    }
    
    /**
     * Get all recipes
     */
    public List<Recipe> getAllRecipes() {
        LOGGER.log(Level.INFO, "Fetching all recipes");
        return mealPlanningDAL.getAllRecipes();
    }
    
    /**
     * Get ingredients for a specific recipe
     */
    public List<Ingredient> getIngredientsForRecipe(String recipeName) {
        LOGGER.log(Level.INFO, "Fetching ingredients for recipe: " + recipeName);
        return mealPlanningDAL.getIngredientsForRecipe(recipeName);
    }
    
    /**
     * Execute GetRecipes stored procedure
     */
    public List<Recipe> getRecipesFromStoredProcedure() {
        LOGGER.log(Level.INFO, "Executing GetRecipes stored procedure");
        return mealPlanningDAL.getRecipesFromStoredProcedure();
    }
} 