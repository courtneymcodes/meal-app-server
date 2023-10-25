package com.example.mealappserver.seed;

import com.example.mealappserver.model.Cart;
import com.example.mealappserver.model.Ingredient;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.model.User;
import com.example.mealappserver.repository.CartRepository;
import com.example.mealappserver.repository.IngredientRepository;
import com.example.mealappserver.repository.RecipeRepository;
import com.example.mealappserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class SeedData implements CommandLineRunner{

    private final PasswordEncoder passwordEncoder;
    RecipeRepository recipeRepository;
    UserRepository userRepository;
    CartRepository cartRepository;

    IngredientRepository ingredientRepository;

    @Autowired
    public SeedData (@Lazy PasswordEncoder passwordEncoder, RecipeRepository recipeRepository, UserRepository userRepository, CartRepository cartRepository, IngredientRepository ingredientRepository) {
        this.passwordEncoder = passwordEncoder;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public SeedData(@Lazy PasswordEncoder passwordEncoder, RecipeRepository recipeRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.passwordEncoder = passwordEncoder;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (recipeRepository.count() == 0) {
            User user = new User();
            user.setName("Person 1");
            user.setEmailAddress("email@email.com");
            user.setPassword(passwordEncoder.encode("password123"));
            userRepository.save(user);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);

            //cart ingredients
            Ingredient ingredient1 = new Ingredient();
            ingredient1.setName("Onions");
            ingredient1.setCart(cart);
            ingredientRepository.save(ingredient1);

            Ingredient ingredient2 = new Ingredient();
            ingredient2.setName("Carrots");
            ingredient2.setCart(cart);
            ingredientRepository.save(ingredient2);

            //recipes
            Recipe recipe1 = new Recipe();
            recipe1.setName("Pizza");
            recipe1.setInstructions("Instructions for making a pizza");
            recipe1.setUser(user);
            recipeRepository.save(recipe1);

            Recipe recipe2 = new Recipe();
            recipe1.setName("Spaghetti");
            recipe1.setInstructions("Instructions for making spaghetti");
            recipe1.setUser(user);
            recipeRepository.save(recipe2);

        }
    }
}
