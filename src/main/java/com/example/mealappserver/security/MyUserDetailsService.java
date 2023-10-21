package com.example.mealappserver.security;

import com.example.mealappserver.model.User;
import com.example.mealappserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * MyUserDetailsService implements Spring Securities UserDetailsService. Used to load user details by email address to be used during authentication.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Loads user details by given email address
     * @param emailAddress of the user
     * @return  UserDetails object containing information about the user
     * @throws UsernameNotFoundException if user with given email address is not found
     */

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userService.getUserByEmailAddress(emailAddress);
        return new MyUserDetails(user);
    }
}
