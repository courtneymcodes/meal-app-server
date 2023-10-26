package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.exception.InformationNotFoundException;
import com.example.mealappserver.model.Cart;
import com.example.mealappserver.model.Ingredient;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.repository.CartRepository;
import com.example.mealappserver.repository.IngredientRepository;
import com.example.mealappserver.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;
    private CartRepository cartRepository;
    private UserService userService;
    private RecipeRepository recipeRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, CartRepository cartRepository, UserService userService, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.recipeRepository = recipeRepository;
    }




    public Ingredient createCartIngredient(Ingredient ingredientObject, Long cartId){
        Cart cart = cartRepository.findByIdAndUserId(cartId, userService.getCurrentLoggedInUser().getId()); //find the users cart in the database
        if (cart != null) {
            Ingredient ingredientExists = ingredientRepository.findByNameAndCartId(ingredientObject.getName(), cartId); //check if an ingredient already exists in the cart
            if (ingredientExists == null) { //if ingredient doesn't exist
                ingredientObject.setCart(cart); //set the ingredient objects cart field to the users cart
                return ingredientRepository.save(ingredientObject); //then save the ingredient with updated cart
            } else {
                throw new InformationExistsException("Ingredient already exists");
            }
        } else {
            throw new InformationNotFoundException("Cart with id" + cartId + " not found");
        }
    }

    public List<Ingredient> getAllCartIngredients(Long cartId) {
        Cart cart = cartRepository.findByIdAndUserId(cartId, userService.getCurrentLoggedInUser().getId()); //get the users cart
        if (cart != null) {
            return ingredientRepository.findAllByCartId(cartId);
        } else {
            throw new InformationNotFoundException("Cart not found");
        }
    }

    public Ingredient deleteCartIngredient(Long cartId, Long ingredientId) {
        Cart cart = cartRepository.findByIdAndUserId(cartId, userService.getCurrentLoggedInUser().getId());
        if (cart != null) {
             Ingredient ingredient = ingredientRepository.findIngredientByIdAndCartId(ingredientId, cartId);
             if (ingredient != null){
                 ingredientRepository.deleteById(ingredientId);
                 return ingredient;
             } else {
                 throw new InformationNotFoundException("Ingredient not found");
             }
        } else {
            throw new InformationNotFoundException("Cart not found");
        }
    }

    public Ingredient createRecipeIngredient(Ingredient ingredientObject, Long recipeId){
        Recipe recipe = recipeRepository.findByIdAndUserId(recipeId, userService.getCurrentLoggedInUser().getId()); //find the user's recipe by id
        if (recipe != null) {
            Ingredient ingredient = ingredientRepository.findByNameAndRecipeId(ingredientObject.getName(), recipe.getId());
            if (ingredient == null) {
                ingredientObject.setRecipe(recipe);
                return ingredientRepository.save(ingredientObject);
            } else {
                throw new InformationExistsException("Ingredient with name " + ingredientObject.getName() + "already exists");
            }
        } else {
            throw new InformationNotFoundException("recipe with id " + recipeId + " not found");
        }
    }

    public Ingredient deleteRecipeIngredient(Long recipeId, Long ingredientId){
        Recipe recipe = recipeRepository.findByIdAndUserId(recipeId, userService.getCurrentLoggedInUser().getId());// check if user has the recipe by finding the recipe by id
        if(recipe != null) {// if recipe exists
            Ingredient ingredient = ingredientRepository.findByIdAndAndRecipeId(ingredientId,recipe.getId());// check if ingredient exists by finding it by its id and recipeId
            if (ingredient != null) { //if it exists, delete it
                ingredientRepository.deleteById(ingredientId);
                return ingredient;
            } else {
                throw new InformationNotFoundException("Ingredient with " + ingredientId + " not found");
            }
        } else {
            throw new InformationNotFoundException("Recipe with " + recipeId + " not found");
        }
    }

}
