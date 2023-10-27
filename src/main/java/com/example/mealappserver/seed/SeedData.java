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

            //recipe1
            Recipe recipe1 = new Recipe();
            recipe1.setName("Pizza");
            recipe1.setInstructions("Instructions for making a pizza");
            recipe1.setImageUrl("https://images.unsplash.com/photo-1613564834361-9436948817d1?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fHBpenphfGVufDB8fDB8fHww");
            recipe1.setSourceUrl("https://www.simplyrecipes.com/recipes/homemade_pizza/");
            recipe1.setUser(user);
            recipeRepository.save(recipe1);

            //recipe1 ingredients
            Ingredient recipeIngredient2 = new Ingredient();
            recipeIngredient2.setName("Cheese");
            recipeIngredient2.setRecipe(recipe1);
            ingredientRepository.save(recipeIngredient2);

            Ingredient recipeIngredient3 = new Ingredient();
            recipeIngredient3.setName("Flour");
            recipeIngredient3.setRecipe(recipe1);
            ingredientRepository.save(recipeIngredient3);

            Ingredient recipeIngredient4 = new Ingredient();
            recipeIngredient4.setName("Tomato Sauce");
            recipeIngredient4.setRecipe(recipe1);
            ingredientRepository.save(recipeIngredient4);

            // recipe2
            Recipe recipe2 = new Recipe();
            recipe2.setName("Spaghetti and Meatballs");
            recipe2.setInstructions("In a Dutch oven, heat olive oil over medium heat. Add onions; saute until softened. Add garlic; cook 1 minute longer. Stir in tomato paste; cook 3-5 minutes. Add next 6 ingredients. Bring to a boil. Reduce heat; simmer, covered, for 50 minutes....");
            recipe2.setImageUrl("https://images.unsplash.com/photo-1551892374-ecf8754cf8b0?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BhZ2hldHRpfGVufDB8fDB8fHww");
            recipe2.setSourceUrl("https://www.bonappetit.com/recipe/easy-spaghetti-and-meatballs");
            recipe2.setUser(user);
            recipeRepository.save(recipe2);

            //Recipe 2 ingredients
            Ingredient recipeIngredient5 = new Ingredient();
            recipeIngredient5.setName("Tomato Sauce");
            recipeIngredient5.setRecipe(recipe2);
            ingredientRepository.save(recipeIngredient5);

            Ingredient recipeIngredient6 = new Ingredient();
            recipeIngredient6.setName("Parmesan Cheese");
            recipeIngredient6.setRecipe(recipe2);
            ingredientRepository.save(recipeIngredient6);

            Ingredient recipeIngredient7 = new Ingredient();
            recipeIngredient7.setName("Ground Beef");
            recipeIngredient7.setRecipe(recipe2);
            ingredientRepository.save(recipeIngredient7);

            // recipe 3
            Recipe recipe3 = new Recipe();
            recipe3.setName("Baked Salmon");
            recipe3.setInstructions("Step 1: Preheat the oven to 350 degrees F (175 degrees C). Place salmon on a lightly oiled sheet pan or in a shallow baking dish, folding under thin outer edges of fillets for even cooking...");
            recipe3.setImageUrl("https://images.unsplash.com/photo-1560717845-968823efbee1?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8c2FsbW9ufGVufDB8fDB8fHww");
            recipe3.setSourceUrl("https://www.allrecipes.com/recipes/15846/main-dish/seafood/salmon/baked-salmon/");
            recipe3.setUser(user);
            recipeRepository.save(recipe3);

            //recipe 3 ingredients
            Ingredient recipeIngredient8 = new Ingredient();
            recipeIngredient8.setName("Salmon Fillet");
            recipeIngredient8.setRecipe(recipe3);
            ingredientRepository.save(recipeIngredient8);

            Ingredient recipeIngredient9 = new Ingredient();
            recipeIngredient9.setName("Onions");
            recipeIngredient9.setRecipe(recipe3);
            ingredientRepository.save(recipeIngredient9);

            Ingredient recipeIngredient10 = new Ingredient();
            recipeIngredient10.setName("Salt");
            recipeIngredient10.setRecipe(recipe3);
            ingredientRepository.save(recipeIngredient10);

            Ingredient recipeIngredient11 = new Ingredient();
            recipeIngredient11.setName("Pepper");
            recipeIngredient11.setRecipe(recipe3);
            ingredientRepository.save(recipeIngredient11);

            //recipe 4
            Recipe recipe4 = new Recipe();
            recipe4.setName("Fettuccine Alfredo");
            recipe4.setInstructions("Step 1: Melt butter into cream in a large saucepan over low heat; add salt, pepper, and garlic salt. Increase the heat to medium; stir in grated Romano and Parmesan cheese until melted and sauce has thickened...");
            recipe4.setImageUrl("https://images.unsplash.com/photo-1645112411341-6c4fd023714a?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmV0dHVjY2luZSUyMGFsZnJlZG98ZW58MHx8MHx8fDA%3D");
            recipe4.setSourceUrl("https://www.thepioneerwoman.com/food-cooking/recipes/a8885/fettuccine-alfredo/");
            recipe4.setUser(user);
            recipeRepository.save(recipe4);

            //recipe 4 ingredients
            Ingredient recipeIngredient12 = new Ingredient();
            recipeIngredient12.setName("Butter");
            recipeIngredient12.setRecipe(recipe4);
            ingredientRepository.save(recipeIngredient12);

            Ingredient recipeIngredient13 = new Ingredient();
            recipeIngredient13.setName("Heavy Cream");
            recipeIngredient13.setRecipe(recipe4);
            ingredientRepository.save(recipeIngredient13);

            Ingredient recipeIngredient14 = new Ingredient();
            recipeIngredient14.setName("Parmesan Cheese");
            recipeIngredient14.setRecipe(recipe4);
            ingredientRepository.save(recipeIngredient14);

            // recipe 5
            Recipe recipe5 = new Recipe();
            recipe5.setName("Macaroni and cheese");
            recipe5.setInstructions("Instructions for making macaroni and cheese");
            recipe5.setImageUrl("https://images.unsplash.com/photo-1543339520-51ebace10a0a?auto=format&fit=crop&q=60&w=500&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8bWFjYXJvbmklMjBhbmQlMjBjaGVlc2V8ZW58MHx8MHx8fDA%3D");
            recipe5.setSourceUrl("https://www.allrecipes.com/recipe/11679/homemade-mac-and-cheese/");
            recipe5.setUser(user);
            recipeRepository.save(recipe5);

            //recipe 5 ingredients
            Ingredient recipeIngredient1 = new Ingredient();
            recipeIngredient1.setName("Cheddar Cheese");
            recipeIngredient1.setRecipe(recipe5);
            ingredientRepository.save(recipeIngredient1);

            Ingredient recipeIngredient15 = new Ingredient();
            recipeIngredient15.setName("Elbow Macaroni");
            recipeIngredient15.setRecipe(recipe5);
            ingredientRepository.save(recipeIngredient15);

            Ingredient recipeIngredient16 = new Ingredient();
            recipeIngredient16.setName("Milk");
            recipeIngredient16.setRecipe(recipe5);
            ingredientRepository.save(recipeIngredient16);
        }
    }
}
