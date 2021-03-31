package com.mealplan.demo.service;

import com.mealplan.demo.dao.MealPlanMapper;
import com.mealplan.demo.entity.Ingredients;
import com.mealplan.demo.entity.Recipes;
import com.mealplan.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MealPlanServices {
    @Autowired
    MealPlanMapper mpm;


    public Users getUserById(int user_id){ return mpm.getUserById(user_id); }

    public void createUser(Users users){mpm.createUser(users);}

    public ArrayList<Integer> getRecipesIdByUserid(int id){return mpm.getRecipesIdByUserid(id);}

    public Recipes getRecipeByRecipeid(int recipeid){return mpm.getRecipeByRecipeid(recipeid);}

    public void deleteUserrecipes(int user_id){mpm.deleteUserrecipes(user_id);}

    public void deleteUser(int user_id){ mpm.deleteUser(user_id);}

    public void updatePassword(String password, int user_id){mpm.updatePassword(password, user_id);}

    public void updateName(String username, int user_id){mpm.updateName(username, user_id);}

    public ArrayList<Recipes> getAllRecipes(){return mpm.getAllRecipes();}

    public void AddFavoriteRecipes(int recipe_id,int user_id, String meal_date,String meal_time){mpm.AddFavoriteRecipesById(recipe_id,user_id,meal_date,meal_time);}

    public void deleteFavoriteRecipesById(int user_id,int recipe_id){mpm.deleteFavoriteRecipesById(user_id,recipe_id);}

    public int  getSecurityLevelByUserId(int user_id){return mpm.getSecurityLevelByUserId(user_id);}

    public void AddRecipes(int recipe_id,String recipe_name,String recipe_desc ){ mpm.AddRecipes(recipe_id,recipe_name,recipe_desc);}

    public ArrayList<Ingredients> getAllIngredients(){return mpm.getAllIngredients();}

    public void insertIngredientById(int ingredient_id , int recipe_id){mpm.insertIngredientById(ingredient_id,recipe_id);}

    public void deleteRecipesingredientById(int recipe_id){mpm.deleteRecipesingredientById(recipe_id);}

    public void deleteRecipesById(int recipe_id){mpm.deleteRecipesById(recipe_id);}

    public void deleteUserecipesById(int recipe_id){mpm.deleteUserecipesById(recipe_id);}

    public ArrayList<Users> getAllCustomer(){return mpm.getallCustomer();}

    public ArrayList<Users> getAllChefs(){return mpm.getallChefs();}
}
