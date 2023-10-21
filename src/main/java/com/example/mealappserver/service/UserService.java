package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.model.User;
import com.example.mealappserver.repository.UserRepository;
import com.example.mealappserver.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    /**
     * Constructor is autowired to inject userRepository and password encoder into UserService class
     *
     * @param userRepository
     * @param passwordEncoder
     * @param jwtUtils
     * @param authenticationManager
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user if email address is unique.
     * @param userObject being created
     * @return userObject with encoded password saved to the database
     * @thorws InofrmationExistsException if a user with the email address already exists in the database
     */
    public User createUser(User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {  //if email doesn't already exist in the database
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));  //encode the password and save to userObject
            return userRepository.save(userObject);  //save userObject with encoded password to database
        } else {  //if email address already exists in database, throw an exception
            throw new InformationExistsException("Email address " + userObject.getEmailAddress() + " already exists");
        }
    }

    /**
     * Retrieves the user from the database by email address
     * @param emailAddress retrieving from the database
     * @return the user object in the database with the given email address
     */
    public User getUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }


}
