package com.example.nidoqueue;

public class UserProfile {
    private String username, email, phone;
    UserProfile(String username, String email, String phone){
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
    String getUsername(){
        return username;
    }
    String getEmail(){
        return email;
    }
    String getPhone(){
        return phone;
    }
}
