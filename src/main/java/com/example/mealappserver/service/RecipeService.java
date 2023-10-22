package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.exception.InformationNotFoundException;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;
    private UserService userService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public Recipe createRecipe(Recipe recipeObject) {
        Recipe recipe = recipeRepository.findByNameAndUserId(recipeObject.getName(), userService.getCurrentLoggedInUser().getId());
        if (recipe == null) {
            recipeObject.setUser(userService.getCurrentLoggedInUser());
            return recipeRepository.save(recipeObject);
        } else {
            throw new InformationExistsException("Recipe with name " + recipeObject.getName() + " alreadyexists");
        }
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe editRecipe(Long recipeId, Recipe recipeObject) {
        Recipe recipe = recipeRepository.findByIdAndUserId(recipeId, userService.getCurrentLoggedInUser().getId());
        if (recipe != null) {
            if (recipe.getName().equals(recipeObject.getName()) && recipe.getInstructions().equals(recipeObject.getInstructions())) {
                throw new InformationExistsException("Recipe with that content already exists");
            } else {
                 recipe.setName(recipe.getName());
                 recipe.setInstructions(recipe.getInstructions());
                 return recipeRepository.save(recipe);
            }
        } else {
            throw new InformationNotFoundException("Recipe with id " + recipeId + " not found");
        }
    }

    public Recipe getRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findByIdAndUserId(recipeId, userService.getCurrentLoggedInUser().getId());
        if (recipe != null){
            return recipe;
        } else {
            throw new InformationNotFoundException("Recipe with id " + recipeId + " not found");
        }
    }

    public Recipe deleteRecipe (Long recipeId) {
        Recipe recipe = recipeRepository.findByIdAndUserId(recipeId, userService.getCurrentLoggedInUser().getId());
        if (recipe != null) {
            recipeRepository.deleteById(recipeId);
            return recipe;
        } else {
            throw new InformationNotFoundException("Recipe not found");
        }
    }
}
