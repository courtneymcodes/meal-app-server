package com.example.mealappserver.repository;

import com.example.mealappserver.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByNameAndCartId(String ingredientName, Long cartId);

    List<Ingredient> findAllByCartId(Long cartId);

    Ingredient findIngredientByIdAndCartId(Long ingredientId, Long cartId);

    Ingredient findByNameAndRecipeId(String ingredientName, Long recipeId);

    Ingredient findByIdAndAndRecipeId(Long ingredientId, Long recipeId);
}
