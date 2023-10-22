package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.model.User;
import com.example.mealappserver.model.request.LoginRequest;
import com.example.mealappserver.repository.UserRepository;
import com.example.mealappserver.security.JWTUtils;
import com.example.mealappserver.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
     *  If user is able to successfully log in a jwt key as string is returned, otherwise throws an error
     * @param loginRequest with username and password
     * @return an optional containing the token string with user details if successful or an empty optional if not successful
     */
    public Optional<String> loginUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()); //log in user with given username and password
        try{
            Authentication authentication = authenticationManager.authenticate(authenticationToken); // authenticate the incoming request
            SecurityContextHolder.getContext().setAuthentication(authentication);  //set security context
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal(); // get user details
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails)); //return the token containing the user details if successful
        }catch (Exception e){
            return Optional.empty();
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

    public User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getUser();
    }


}
