package com.example.mealappserver.controller;

import com.example.mealappserver.model.User;
import com.example.mealappserver.model.request.LoginRequest;
import com.example.mealappserver.model.response.LoginResponse;
import com.example.mealappserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user with the information from the request body
     * @param userObject
     * @return the user object
     */
    @PostMapping(path = "/register/")
    public User createUser(@RequestBody User userObject){
        return userService.createUser(userObject);
    }

    /**
     * Method logs a user in when they provide their valid credentials. If authentication is successful and the user logs in, a jwt token is returned in the response
     * @param loginRequest
     * @return ResponseEntity containing jwt token if log iin successful or unauthorized response if not
     */
    @PostMapping(path = "/login/")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<String> jwtToken = userService.loginUser(loginRequest);
        if (jwtToken.isPresent()){
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        } else {  //login unsuccessful
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
        }
    }

}
