package com.example.mealappserver.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {


    /**
     * Every request from server must go through SecurityFilterChain which determines whether access is allowed.
     * @param http incoming http request
     * @return configured SecurityFilterChain
     * @throws Exception if error occurs
     */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/auth/users","/auth/users/login/","/auth/users/register/").permitAll()  //allow access to this list of urls
                .antMatchers("/h2-console/**").permitAll() // allow access to any url starting with /h2-console/
                .anyRequest().authenticated()  //must authenticate any request
                .and().sessionManagement()  //must manage user session
                .and().csrf().disable() //disable Cross-Site Request Forgery protection
                .headers().frameOptions().disable();  //for rendering h2 database
        return http.build();
    }
}
