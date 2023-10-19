package com.example.mealappserver.repository;

import com.example.mealappserver.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface recipeRepository extends JpaRepository<Recipe, Long> {
}
