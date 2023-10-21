package com.example.mealappserver.repository;

import com.example.mealappserver.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User findUserByEmailAddress(String emailAddress);
}
