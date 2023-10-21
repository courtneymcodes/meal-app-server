package com.example.mealappserver.security;

import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

//
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;


}
