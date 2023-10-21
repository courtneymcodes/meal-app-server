package com.example.mealappserver.repository;

import com.example.mealappserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository that extends the JPA interface and provides common crud operations for User entity. Custom query methods are defined to check if an email address exists in the database and to retrieve a user from the database by their email address.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAddress(String emailAddress);
    User findUserByEmailAddress(String emailAddress);
}
