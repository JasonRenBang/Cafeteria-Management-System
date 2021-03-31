package com.mealplan.demo.controller;


import com.mealplan.demo.entity.Ingredients;
import com.mealplan.demo.entity.Recipes;
import com.mealplan.demo.entity.Users;
import com.mealplan.demo.service.MealPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Controller
public class MealPlanController {
    @Autowired
    MealPlanServices mps;


    @RequestMapping("/home")
    public String home() {
        return "Home";
    }


    @RequestMapping("/login")
    public String login() {
        return "Login";
    }

    @RequestMapping("/dologin")

    public String dologin(HttpServletRequest request) {

        boolean b;
        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        Users user = mps.getUserById(id);
        if (user.checkPassword(request.getParameter("password"))) {

            String address = "redirect:/userhome/" + id;
            return address;
        } else {
            return "Login";
        }
    }

    @RequestMapping("/Cheflogin")
    public String Cheflogin() {
        return "Cheflogin";
    }

    @RequestMapping("/doCheflogin")
    public String doCheflogin(HttpServletRequest request) {

        boolean b;
        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        //厨师的安全级别为2 ,安全级别高于2的用户无法登录
        if (mps.getSecurityLevelByUserId(id) >2) {
            return "Home";
        }
        Users user = mps.getUserById(id);
        if (user.checkPassword(request.getParameter("password"))) {

            String address = "redirect:/Chefhome/" + id;
            return address;
        } else {
            return "Home";
        }
    }

    @RequestMapping("/Adminlogin")

    public String mapper(){
        return "Adminlogin";
    }

    @RequestMapping("/doAdminlogin")
    public String doAdminlogin(HttpServletRequest request) {

        boolean b;
        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        //管理员的安全级别为1,其他级别的人无法登录管理员
        if (mps.getSecurityLevelByUserId(id) != 1) {
            //登陆错误则返回home页面
            return "Home";
        }
        Users user = mps.getUserById(id);
        if (user.checkPassword(request.getParameter("password"))) {

            return "redirect:/Adminhome/" + id;
        } else {
            return "Home";
        }
    }

    @RequestMapping("/Adminhome/{id}")
    public String AdminHome(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("id", id);
        model.addAttribute("name", mps.getUserById(id).getUsername());
        return "AdminHomePage";
    }


    @RequestMapping("/Register")
    public String createAccount() {
        return "Registration";
    }

    @RequestMapping("/doRegister")
    public String doCreateAccount(HttpServletRequest request) {

        //获得id
        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        //获得username
        String username = request.getParameter("username");
        //获得password
        String password = request.getParameter("password");
        //customer's security level default as 3
        Users u = new Users(id, username, password, 3);
        mps.createUser(u);
        return "Home";
    }

    @RequestMapping("/Chefhome/{id}")
    public String ChefHome(@PathVariable("id") Integer id, Model model) {
        ArrayList<Recipes> Allrecipes = mps.getAllRecipes();
        model.addAttribute("AllRecipes", Allrecipes);
        model.addAttribute("id", id);
        return "ChefHomePage";
    }

    @RequestMapping("/userhome/{id}")
    public String UsersHome(@PathVariable("id") Integer id, Model model) {

        ArrayList<Integer> arr = mps.getRecipesIdByUserid(id);
        ArrayList<Recipes> recipesList = new ArrayList<Recipes>();
        //向前端传输该用户的id
        model.addAttribute("id", id);
        model.addAttribute("name", mps.getUserById(id).getUsername());
        for (int id1 : arr) {
            recipesList.add(mps.getRecipeByRecipeid(id1));
        }
        //向前端传输该用户菜谱
        model.addAttribute("Recipes", recipesList);
        return "Userhome";
    }


    @RequestMapping("/userhome/{id}/DeleteAccount")
    public String DeleteAccount(@PathVariable("id") Integer id) {
        mps.deleteUserrecipes(id);
        mps.deleteUser(id);
        return "redirect:/home";
    }

    @RequestMapping("/userhome/{id}/UpdateAccount")
    public String UpdateAccount(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return "UpdateAccount";
    }

    @RequestMapping("/userhome/{id}/doUpdateAccount")
    public String doUpdateAccount(HttpServletRequest request, @PathVariable("id") Integer id) {
        //update user's name
        mps.updateName(request.getParameter("username"), id);
        //update users's password
        mps.updatePassword(request.getParameter("password"), id);
        //return users home page
        String address = "redirect:/userhome/" + id;
        return address;
    }

    @RequestMapping("/userhome/{id}/AddFavoriteRecipes")
    public String AddFavoriteRecipes(@PathVariable("id") Integer id, Model model) {
        ArrayList<Recipes> Allrecipes = mps.getAllRecipes();
        model.addAttribute("AllRecipes", Allrecipes);
        model.addAttribute("id", id);
        ArrayList<Integer> arr = mps.getRecipesIdByUserid(id);
        ArrayList<Recipes> recipesList = new ArrayList<Recipes>();
        for (int id1 : arr) {
            recipesList.add(mps.getRecipeByRecipeid(id1));
        }
        model.addAttribute("MyRecipes", recipesList);
        return "AddFavoriteRecipes";
    }

    @RequestMapping("/userhome/{id}/doAddFavoriteRecipes")
    public String doAddFavoriteRecipes(HttpServletRequest request, @PathVariable("id") Integer id) {
        int recipes_id = Integer.parseInt(request.getParameter("recipes_id"));
        ArrayList<Integer> idList = mps.getRecipesIdByUserid(id);
        if (!idList.contains(recipes_id)) {
            String date = LocalDate.now().toString();
            String time = LocalTime.now().toString();
            mps.AddFavoriteRecipes(recipes_id, id, date, time);
        }
        return "redirect:/userhome/" + id + "/AddFavoriteRecipes";
    }

    @RequestMapping("/userhome/{id}/DeleteFavoriteRecipes")
    public String DeleteFavoriteRecipes(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        ArrayList<Integer> arr = mps.getRecipesIdByUserid(id);
        ArrayList<Recipes> recipesList = new ArrayList<Recipes>();
        for (int id1 : arr) {
            recipesList.add(mps.getRecipeByRecipeid(id1));
        }
        model.addAttribute("MyRecipes", recipesList);
        return "DeleteFavoriteRecipes";
    }

    @RequestMapping("/userhome/{id}/doDeleteFavoriteRecipes")
    public String doDeleteFavoriteRecipes(HttpServletRequest request, @PathVariable("id") Integer id) {
        int recipes_id = Integer.parseInt(request.getParameter("recipes_id"));
        mps.deleteFavoriteRecipesById(id, recipes_id);
        return "redirect:/userhome/" + id + "/DeleteFavoriteRecipes";
    }

    @RequestMapping("/Chefhome/{id}/AddRecipes")
    public String AddRecipes(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        ArrayList<Ingredients> ingre = mps.getAllIngredients();
        model.addAttribute("allIngredients", ingre);
        return "AddRecipes";
    }

    @RequestMapping("/Chefhome/{id}/doAddRecipes")
    public String doAddRecipes(HttpServletRequest request, @PathVariable("id") Integer id) {
       int recipes_id = Integer.parseInt(request.getParameter("recipes_id"));
       String recipes_name = request.getParameter("recipes_name");
       String recipes_desc = request.getParameter("recipes_desc");
       mps.AddRecipes(recipes_id, recipes_name, recipes_desc);

        String str = request.getParameter("ingredients_ids");
        String[] Str1 = str.split(" ");

        for (String s : Str1) {
         int tempid=Integer.parseInt(s);
         mps.insertIngredientById(tempid,recipes_id);
        }
        return "redirect:/Chefhome/" + id + "/AddRecipes";
    }

    @RequestMapping("Chefhome/{id}/DeleteRecipes")
    public String DeleteRecipes(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("id", id);
        ArrayList<Recipes> recipes1 = mps.getAllRecipes();
        model.addAttribute("AllRecipes", recipes1);
        return "DeleteRecipes";
    }

    @RequestMapping("Chefhome/{id}/doDeleteRecipes")

    public String doDeleteRecipes(HttpServletRequest request, @PathVariable("id") Integer id)
    {
        int recipes_id = Integer.parseInt(request.getParameter("recipes_id"));
        mps.deleteUserecipesById(recipes_id);
        mps.deleteRecipesingredientById(recipes_id);
        mps.deleteRecipesById(recipes_id);
        return "redirect:/Chefhome/" + id + "/DeleteRecipes";
    }

    @RequestMapping("Adminhome/{id}/viewAllCustomers")
    public String viewAllCustomer(@PathVariable("id") Integer id,Model model)
    {
        ArrayList<Users> allCustomer=mps.getAllCustomer();
        model.addAttribute("allCustomer",allCustomer);
        model.addAttribute("id", id);
        return "ViewAllCustomer";
    }

    @RequestMapping("Adminhome/{id}/DeleteCustomersByAdmin")
    public String DeleteUsersByAdmin(@PathVariable("id") Integer id, Model model)
    {
        ArrayList<Users> allCustomer=mps.getAllCustomer();
        model.addAttribute("allCustomer",allCustomer);
        model.addAttribute("id", id);
        return "DeleteCustomersByAdmin";
    }

    @RequestMapping("Adminhome/{id}/doDeleteCustomersByAdmin")
    public String doDeleteCustomersByAdmin(HttpServletRequest request, @PathVariable("id") Integer id)
    {
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        mps.deleteUserrecipes(customer_id);
        mps.deleteUser(customer_id);
        return "redirect:/Adminhome/" + id + "/DeleteCustomersByAdmin";
    }

    @RequestMapping("Adminhome/{id}/viewAllChefs")
    public String viewAllChef(@PathVariable("id") Integer id,Model model)
    {
        ArrayList<Users> allChef=mps.getAllChefs();
        model.addAttribute("allChef",allChef);
        model.addAttribute("id", id);
        return "ViewAllChef";
    }

    @RequestMapping("Adminhome/{id}/hireChefs")
    public String createAccount(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("id",id);
        return "RegistrationChefByAdmin";
    }

    @RequestMapping("Adminhome/{id}/doRegistrationChef")

    public String doRegistrationChef(HttpServletRequest request,@PathVariable("id") Integer id,Model model) {
        model.addAttribute(id);
        //获得id
        String str = request.getParameter("id");
        int id2 = Integer.parseInt(str);
        //获得username
        String username = request.getParameter("username");
        //获得password
        String password = request.getParameter("password");
        //chef's security level default as 2
        Users u = new Users(id2, username, password, 2);
        mps.createUser(u);
        return "redirect:/Adminhome/" + id;
    }

    @RequestMapping("Adminhome/{id}/dismissedChefs")
    public String dismissedChefs(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("id",id);
        ArrayList<Users> allChef=mps.getAllChefs();
        model.addAttribute("allChef",allChef);
        return "dismissedChefsByAdmin";
    }

    @RequestMapping("Adminhome/{id}/doDismissedChefsByAdmin")
    public String dodismissedChefs(HttpServletRequest request, @PathVariable("id") Integer id) {
        int chef_id = Integer.parseInt(request.getParameter("chef_id"));
        mps.deleteUserrecipes(chef_id);
        mps.deleteUser(chef_id);
        return "redirect:/Adminhome/" + id + "/dismissedChefs";
    }

}
