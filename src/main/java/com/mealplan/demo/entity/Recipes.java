package com.mealplan.demo.entity;



public class Recipes {
    private int recipe_id;
    private String recipe_name;
    private String recipe_desc;

    public Recipes() {
        recipe_id = 0;
        recipe_name = null;
        recipe_desc = null;
    }
    public Recipes(int recipe_id, String recipe_name, String recipe_desc) {
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.recipe_desc = recipe_desc;
    }
    public int getId() {
        return recipe_id;
    }
    public String getName() {
        return recipe_name;
    }
    public String getDesc() {
        return recipe_desc;
    }
    public int setId(int recipe_id) {
        this.recipe_id = recipe_id;
        return this.recipe_id;
    }
    public String setName(String recipe_name) {
        this.recipe_name = recipe_name;
        return this.recipe_name;
    }
    public String setDesc(String desc) {
        this.recipe_desc = desc;
        return this.recipe_desc;
    }
    public String toString() {
        return recipe_name+": "+recipe_desc;
    }

}

