package com.mealplan.demo.entity;


public class UsersRecipes {
    private int user_id;
    private int recipe_id;
    private String meal_date;
    private String meal_time;

    public UsersRecipes() {
        user_id = 0;
        recipe_id = 0;
        meal_date = null;
        meal_time = null;
    }
    public UsersRecipes(int user_id, int recipe_id, String meal_date, String meal_time) {
        this.user_id = user_id;
        this.recipe_id = recipe_id;
        this.meal_date = meal_date;
        this.meal_time = meal_time;
    }
    public int getUsersId() {
        return user_id;
    }
    public int getRecipesId() {
        return recipe_id;
    }
    public String getMealDate() {
        return meal_date;
    }
    public String getMealTime() {
        return meal_time;
    }
    public int setUsersId(int user_id) {
        this.user_id = user_id;
        return this.user_id;
    }
    public  int setRecipesId(int recipe_id) {
        this.recipe_id = recipe_id;
        return this.recipe_id;
    }
    public String setMealDate(String meal_date) {
        this.meal_date = meal_date;
        return this.meal_date;
    }
    public String setMealTime(String mealTime) {
        this.meal_time = mealTime;
        return this.meal_time;
    }
    public String toString() {
        return recipe_id+": "+ user_id+": "+meal_date+": "+ meal_time;
    }
}

