package com.example.mealappserver.repository;

import com.example.mealappserver.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByNameAndCartId(String ingredientName, Long cartId);
}
