package com.example.mealappserver.model.request;

public class LoginRequest {
    private String emailAddress;
    private String password;

    /**
     * Gets the email address from login request
     * @return emailAddress provided during login
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Gets the password from the login request
     * @return password provided during login
     */
    public String getPassword() {
        return password;
    }
}
