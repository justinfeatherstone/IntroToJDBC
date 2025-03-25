package business.dto;

public class Recipe {
    private String recipeName;
    private String cookbookName;
    private int totalServings;
    private boolean isBook;
    private String website;
    
    public Recipe(String recipeName, String cookbookName, int totalServings, boolean isBook, String website) {
        this.recipeName = recipeName;
        this.cookbookName = cookbookName;
        this.totalServings = totalServings;
        this.isBook = isBook;
        this.website = website;
    }
    
    // Getters and setters
    public String getRecipeName() {
        return recipeName;
    }
    
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    
    public String getCookbookName() {
        return cookbookName;
    }
    
    public void setCookbookName(String cookbookName) {
        this.cookbookName = cookbookName;
    }
    
    public int getTotalServings() {
        return totalServings;
    }
    
    public void setTotalServings(int totalServings) {
        this.totalServings = totalServings;
    }
    
    public boolean isBook() {
        return isBook;
    }
    
    public void setBook(boolean isBook) {
        this.isBook = isBook;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    
    @Override
    public String toString() {
        return "Recipe: " + recipeName + 
               ", Cookbook: " + cookbookName + 
               ", Servings: " + totalServings + 
               (isBook ? ", From Book" : ", From Website: " + website);
    }
} 