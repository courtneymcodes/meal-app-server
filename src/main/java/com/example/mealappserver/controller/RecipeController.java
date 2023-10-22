package com.example.mealappserver.controller;

import com.example.mealappserver.exception.InformationNotFoundException;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/recipes/")
    public ResponseEntity<?> GetAllRecipes() {
        List<Recipe> recipeList = recipeService.getAllRecipes();
        if (recipeList != null) {
            message.put("message", "Recipe favorites list retrieved");
            message.put("data", recipeList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Unable to retrieve recipe favorites list");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/recipes/{recipeId}/")
    public ResponseEntity<?> getRecipe(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (recipe != null) {
            message.put("message", "Recipe with " + recipeId + " retrieved");
            message.put("data", recipe);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Recipe with " + recipeId + " not found");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/recipes/{recipeId}/")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.deleteRecipe(recipeId);
        if (recipe != null) {
            message.put("message", "Recipe with id " + recipeId + " has been deleted");
            message.put("data", recipe);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Not able to delete recipe");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
