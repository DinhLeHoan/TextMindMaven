/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.entity;

/**
 *
 * @author hoanl
 */
public class User {
    private String uID,name,username,password,email;
    private boolean isOnline,isAdmin;
    public User() {
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(String uID, String name, String username, String password, String email) {
        this.uID = uID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public User(String uID, String name, String username, String password, String email, boolean isAdmin) {
        this.uID = uID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
    
    

    public User(String uID, String name,boolean isOnline) {
        this.uID = uID;
        this.name = name;
        this.isOnline = isOnline;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String uID, String name, String username, String password) {
        this.uID = uID;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
    
    
    
}
