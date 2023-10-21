package com.example.mealappserver.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.logging.Logger;

//
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;

    /**
     * When a user logs in, tthe generateJWTMethod will generate a new JSON Web Token (JWT) containing the header, payload and signiture.
     * @param myUserDetails
     * @return a JSON Web Token
     */
    public String generateJwtToken(MyUserDetails myUserDetails) {
        return Jwts.builder()  //build the payload
                .setSubject((myUserDetails.getUsername()))  //email address
                .setIssuedAt(new Date())  // issued at current time
                .setExpiration(new Date((new Date()).getTime() + jwtExpMS))  //current date + jwt expiration date
                .signWith(SignatureAlgorithm.HS256, jwtSecret)  //uses Sha256 algorithm with secret ket
                .compact();
    }
}