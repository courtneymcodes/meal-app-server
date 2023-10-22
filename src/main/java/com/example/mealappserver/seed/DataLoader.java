package com.example.mealappserver.seed;

import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.model.User;
import com.example.mealappserver.repository.RecipeRepository;
import com.example.mealappserver.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    RecipeRepository recipeRepository;
    UserRepository userRepository;

    public DataLoader(@Lazy PasswordEncoder passwordEncoder, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (recipeRepository.count() == 0) {
            User user = new User();
            user.setEmailAddress("email@email.com");
            user.setPassword(passwordEncoder.encode("password123"));
            userRepository.save(user);

            Recipe recipe1 = new Recipe();
            recipe1.setName("Pizza");
            recipe1.setInstructions("Instructions");
            recipe1.setUser(user);
            recipeRepository.save(recipe1);
        }
    }
}
