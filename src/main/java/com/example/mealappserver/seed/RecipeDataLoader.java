package com.example.mealappserver.seed;

import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RecipeDataLoader implements CommandLineRunner {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (recipeRepository.count() == 0) {
            Recipe recipe1 = new Recipe();
            recipe1.setName("Pizza");
            recipeRepository.save(recipe1);
        }
    }
}
