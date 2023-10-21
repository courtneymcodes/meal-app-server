package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.model.User;
import com.example.mealappserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor is autowired to inject userRepository and password encoder into UserService class
     * @param userRepository
     * @param passwordEncoder
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //create method to create a user that takes in a userObject
    //make sure they do not already exist in the database
    //if not, return the saved the user to the database (encode password)
    //if so throw InformationExistsException
    public User createUser(User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {  //if email doesn't already exist in the database
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));  //encode the password and save to userObject
            return userRepository.save(userObject);  //save userObject with encoded password to database
        } else {  //if email address already exists in database, throw an exception
            throw new InformationExistsException("Email address " + userObject.getEmailAddress() + " already exists");
        }
    }
}
