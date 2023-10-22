package com.example.mealappserver.repository;

import com.example.mealappserver.model.Cart;
import com.example.mealappserver.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUserId(Long userId);

    Cart findByNameAndUserId(String recipeName, Long userId);
}
