package dto;

public class Recipe {
    private String recipeName;
    private String cookbookName;
    private Integer totalServings;
    private Boolean isBook;
    private String website;

    public Recipe() {
    }

    public Recipe(String recipeName, String cookbookName, Integer totalServings) {
        this.recipeName = recipeName;
        this.cookbookName = cookbookName;
        this.totalServings = totalServings;
    }

    public Recipe(String recipeName, String cookbookName, Integer totalServings, Boolean isBook, String website) {
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

    public Integer getTotalServings() {
        return totalServings;
    }

    public void setTotalServings(Integer totalServings) {
        this.totalServings = totalServings;
    }

    public Boolean getIsBook() {
        return isBook;
    }

    public void setIsBook(Boolean isBook) {
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
                "\nCookbook: " + cookbookName +
                "\nServings: " + totalServings +
                (isBook != null ? "\nIs Book: " + isBook : "") +
                (website != null ? "\nWebsite: " + website : "");
    }
}