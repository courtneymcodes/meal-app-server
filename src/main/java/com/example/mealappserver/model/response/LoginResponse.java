package com.example.mealappserver.model.response;

/**
 * Used to return the user's jwt when user logs in
 */
public class LoginResponse {
    private String jwt;

    public LoginResponse(String jwt) {

        this.jwt = jwt;
    }

    public String getJwt() {

        return jwt;
    }

    public void setJwt(String jwt) {

        this.jwt = jwt;
    }
}
