package com.example.mealappserver.repository;

import com.example.mealappserver.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByNameAndUserId(String recipeName, Long userId);

    Recipe findByIdAndUserId(Long recipeId, Long userId);


    List<Recipe> findAllByUserId(Long recipeId);
}
