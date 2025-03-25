package dto;

public class Ingredient {
    private Integer id;
    private String ingredientName;
    private String ingredientType;

    public Ingredient() {
    }

    public Ingredient(Integer id, String ingredientName, String ingredientType) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "Ingredient: " + ingredientName +
                "\nType: " + ingredientType +
                "\nID: " + id;
    }
}