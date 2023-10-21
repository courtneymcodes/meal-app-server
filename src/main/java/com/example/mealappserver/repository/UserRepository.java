package com.example.mealappserver.repository;

import com.example.mealappserver.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    boolean existsByEmailAddress(String emailAddress);
    User findUserByEmailAddress(String emailAddress);
}
