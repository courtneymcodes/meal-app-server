package com.example.mealappserver.model;

public class User {
    private Long id;

    private String name;

    private String emailAddress;

    private String password;

    public User() {
    }

    public User(String name, String emailAddress, String password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }


}
