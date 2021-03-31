package com.mealplan.demo.entity;


public class RecipesIngredients {
    private int ingredient_id;
    private int recipe_id;


    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }


    public RecipesIngredients() {
        ingredient_id = 0;
        recipe_id = 0;

    }
    public RecipesIngredients(int ingredient_id, int recipe_id) {
        this.ingredient_id = ingredient_id;
        this.recipe_id = recipe_id;
    }

}
