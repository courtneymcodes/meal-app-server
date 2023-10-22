package com.example.mealappserver.controller;

import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class RecipeController {

    RecipeService recipeService;
    public HashMap<String, Object> message = new HashMap<>();

    @Autowired
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(path = "/recipes/")
    public ResponseEntity<?> createRecipe(@RequestBody Recipe recipeObject) {
        Recipe newRecipe = recipeService.createRecipe(recipeObject);
        if (newRecipe != null) {
            message.put("message", "Recipe has been created successfully");
            message.put("data", newRecipe);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Unable to create recipe");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
