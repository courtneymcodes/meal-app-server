package com.example.mealappserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private MyUserDetailsService myUserDetailsService;
    private JWTUtils jwtUtils;
    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }
    @Autowired
    public void setJwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Extracts JWT from the Authorization header of the http request object
     * @param request HTTPServletRequest to the server
     * @return the extracted JWT as a String or null if not found
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    /**
     * Everytime request is sent it checks for a jwt, validates it, establishes user authentication and sets the authenticated user's details in the security context.
     * @param request incoming HttpServletRequest
     * @param response outgoing HttpServletResponse
     * @param filterChain to pass on request / response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);  //call parseJwt method passing in the incoming request to extract jwt from request authorization header
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {  //if authorization exists and is extracted, validate it by calling method
                String username = jwtUtils.getUsernameFromJwtToken(jwt);  //get the username from the jwt
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username); //load the user's information related to authorization
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());  //validate user
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //request is valid
                SecurityContextHolder.getContext().setAuthentication(authentication); //set context so user can have access
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response); //if successful user is given access, if not error response is sent
    }
}
