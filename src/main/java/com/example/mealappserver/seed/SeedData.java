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
            recipe1.setImageUrl("https://images.unsplash.com/photo-1613564834361-9436948817d1?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fHBpenphfGVufDB8fDB8fHww");
            recipe1.setUser(user);
            recipeRepository.save(recipe1);

            Recipe recipe2 = new Recipe();
            recipe2.setName("Spaghetti");
            recipe2.setInstructions("Instructions for making spaghetti");
            recipe2.setImageUrl("https://images.unsplash.com/photo-1551892374-ecf8754cf8b0?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BhZ2hldHRpfGVufDB8fDB8fHww");
            recipe2.setUser(user);
            recipeRepository.save(recipe2);

            Recipe recipe3 = new Recipe();
            recipe3.setName("Salmon");
            recipe3.setInstructions("Instructions for making salmon");
            recipe3.setImageUrl("https://images.unsplash.com/photo-1560717845-968823efbee1?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8c2FsbW9ufGVufDB8fDB8fHww");
            recipe3.setUser(user);
            recipeRepository.save(recipe3);

            Recipe recipe4 = new Recipe();
            recipe4.setName("Fettuccine Alfredo");
            recipe4.setInstructions("Instructions for making fettuccine");
            recipe4.setImageUrl("https://images.unsplash.com/photo-1645112411341-6c4fd023714a?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmV0dHVjY2luZSUyMGFsZnJlZG98ZW58MHx8MHx8fDA%3D");
            recipe4.setUser(user);
            recipeRepository.save(recipe4);

            Recipe recipe5 = new Recipe();
            recipe5.setName("Macaroni and cheese");
            recipe5.setInstructions("Instructions for making macaroni and cheese");
            recipe5.setImageUrl("https://images.unsplash.com/photo-1543339520-51ebace10a0a?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8bWFjYXJvbmklMjBhbmQlMjBjaGVlc2V8ZW58MHx8MHx8fDA%3D");
            recipe5.setUser(user);
            recipeRepository.save(recipe5);

        }
    }
}
