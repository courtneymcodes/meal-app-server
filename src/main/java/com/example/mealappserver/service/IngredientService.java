package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.exception.InformationNotFoundException;
import com.example.mealappserver.model.Cart;
import com.example.mealappserver.model.Ingredient;
import com.example.mealappserver.repository.CartRepository;
import com.example.mealappserver.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;
    private CartRepository cartRepository;
    private UserService userService;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, CartRepository cartRepository, UserService userService) {
        this.ingredientRepository = ingredientRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
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
        //find all
        Cart cart = cartRepository.findByIdAndUserId(cartId, userService.getCurrentLoggedInUser().getId()); //get the users cart
        if (cart != null) {
            return ingredientRepository.findAllByCartId(cartId);
        } else {
            throw new InformationNotFoundException("Cart not found");
        }

    }

}
