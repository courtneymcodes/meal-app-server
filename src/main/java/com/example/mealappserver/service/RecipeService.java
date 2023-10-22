package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
