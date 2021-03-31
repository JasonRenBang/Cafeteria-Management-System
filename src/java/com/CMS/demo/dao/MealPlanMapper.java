package com.mealplan.demo.dao;

import com.mealplan.demo.entity.Ingredients;
import com.mealplan.demo.entity.Recipes;
import com.mealplan.demo.entity.RecipesIngredients;
import com.mealplan.demo.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface MealPlanMapper {
    //通过用户的Id获得用户数据
    @Select("select * from users WHERE user_id =#{user_id}")
    Users getUserById(int user_id);

    @Insert("insert into users(user_id, username, user_password, security_level) VALUE " +
            "(#{user_id},#{username},#{user_password},#{security_level})")
    void createUser(Users users);

    @Select("select recipe_id from usersrecipes WHERE user_id = #{id}  ")
    ArrayList<Integer> getRecipesIdByUserid(int id);

    @Select("select * from recipes WHERE recipe_id = #{recipeid}")
    Recipes getRecipeByRecipeid(int recipeid);

    @Delete ("delete from usersrecipes WHERE user_id = #{user_id} ")
    void deleteUserrecipes(int user_id);

    @Delete ("delete from users WHERE user_id = #{user_id} ")
    void deleteUser(int user_id);

    @Update("update users SET user_password = #{password} WHERE user_id = #{user_id} ")
     void updatePassword(String password, int user_id);

    @Update("update users SET username = #{username} WHERE user_id = #{user_id} ")
     void updateName(String username, int user_id);

    @Select("select * from recipes")
    ArrayList<Recipes> getAllRecipes();

    @Insert("insert into usersrecipes(recipe_id,user_id,meal_date,meal_time) VALUE (#{recipe_id},#{user_id},#{meal_date},#{meal_time})")
    void AddFavoriteRecipesById(int recipe_id,int user_id, String meal_date,String meal_time);

    @Delete ("delete from usersrecipes WHERE user_id = #{user_id} and recipe_id=#{recipe_id}")
    void deleteFavoriteRecipesById(int user_id,int recipe_id);

    @Select("select security_level from users Where  user_id = #{user_id}")
    int getSecurityLevelByUserId(int user_id);

    @Insert("insert into recipes(recipe_id,recipe_name,recipe_desc) VALUE (#{recipe_id},#{recipe_name},#{recipe_desc})")
    void AddRecipes(int recipe_id,String recipe_name,String recipe_desc );

    @Select("select * from ingredients")
    ArrayList<Ingredients> getAllIngredients();

    @Insert("insert into recipesingredients(ingredient_id,recipe_id) VALUE (#{ingredient_id},#{recipe_id})")
    void insertIngredientById(int ingredient_id , int recipe_id);

    @Delete ("delete from recipesingredients WHERE recipe_id=#{recipe_id}")
    void deleteRecipesingredientById(int recipe_id);

    @Delete("delete from recipes WHERE recipe_id=#{recipe_id}")
    void deleteRecipesById(int recipe_id);

    @Delete("delete from usersrecipes WHERE recipe_id=#{recipe_id}")
    void deleteUserecipesById(int recipe_id);

    @Select("select * from users Where security_level=3")
    ArrayList<Users> getallCustomer();

    @Select("select * from users Where security_level=2")
    ArrayList<Users> getallChefs();
}
