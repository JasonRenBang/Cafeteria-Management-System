package com.mealplan.demo.entity;


public class Users {
    private int user_id;
    private String username;
    private String user_password;
    private int security_level;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getSecurity_level() {
        return security_level;
    }

    public void setSecurity_level(int security_level) {
        this.security_level = security_level;
    }

    public Users() {
        user_id = 0;
        username = null;
        user_password = null;
        security_level = 1;
    }
    public Users(int user_id, String username, String user_password, int security_level) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.security_level = security_level;
    }



    @Override
    public String toString() {
        return user_id+": "+username+": "+ user_password+": "+security_level;
    }
    public boolean checkPassword(String pass) {
        boolean check = false;
        if(this.user_password.equals(pass)) {
            check = true;
            return check;
        }
        else
            return check;
    }

}
