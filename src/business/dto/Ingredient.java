package business.dto;

public class Ingredient {
    private int id;
    private String ingredientName;
    private String ingredientType;
    
    public Ingredient(int id, String ingredientName, String ingredientType) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getIngredientName() {
        return ingredientName;
    }
    
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    
    public String getIngredientType() {
        return ingredientType;
    }
    
    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }
    
    @Override
    public String toString() {
        return ingredientName + " (" + ingredientType + ")";
    }
} 