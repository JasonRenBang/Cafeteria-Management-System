package com.mealplan.demo.entity;



public class Ingredients {
    private int ingredient_id;
    private String ingredient_name;
    private int g_fiber;
    private int g_sugar;
    private int g_protein;

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getG_fiber() {
        return g_fiber;
    }

    public void setG_fiber(int g_fiber) {
        this.g_fiber = g_fiber;
    }

    public int getG_sugar() {
        return g_sugar;
    }

    public void setG_sugar(int g_sugar) {
        this.g_sugar = g_sugar;
    }

    public int getG_protein() {
        return g_protein;
    }

    public void setG_protein(int g_protein) {
        this.g_protein = g_protein;
    }

    public Ingredients() {
        this.ingredient_id = 0;
        this.ingredient_name = null;
        this.g_fiber = 0;
        this.g_sugar = 0;
        this.g_protein = 0;

    }

    public Ingredients(int ingredient_id, String ingredient_name, String ingredient_desc, String serving_metric, int g_unsaturated_fat, int g_saturated_fat, int g_trans_fat, int mg_cholesterol, int mg_sodium, int g_fiber, int g_sugar, int g_protein) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
        this.g_fiber = g_fiber;
        this.g_sugar = g_sugar;
        this.g_protein = g_protein;
    }

}

