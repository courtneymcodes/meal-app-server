package com.example.mealappserver.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;

    /**
     * When a user logs in, the generateJWTMethod will generate a new JSON Web Token (JWT) containing the header, payload and signature.
     * @param myUserDetails
     * @return a JSON Web Token with payload
     */
    public String generateJwtToken(MyUserDetails myUserDetails) {
        return Jwts.builder()  //build the payload
                .setSubject((myUserDetails.getUsername()))  //email address
                .setIssuedAt(new Date())  // issued at current time
                .setExpiration(new Date((new Date()).getTime() + jwtExpMS))  //current date + jwt expiration date
                .signWith(SignatureAlgorithm.HS256, jwtSecret)  //uses Sha256 algorithm with secret ket
                .compact();
    }

    /**
     * Retrieves the username (user's email address) from JSON Web Token
     * @param token The JWT to extract the username from
     * @return the subject (username) extracted from the JWT
     */
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validates the JWT token
     * @param token JWT being validated
     * @return true if JWT is valid. Returns false if not valid or exception occurs
     * throws exception if token invalid
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.log(Level.SEVERE, "Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.log(Level.SEVERE, "JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}