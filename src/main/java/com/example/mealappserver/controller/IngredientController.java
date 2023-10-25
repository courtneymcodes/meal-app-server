package com.example.mealappserver.controller;

import com.example.mealappserver.model.Ingredient;
import com.example.mealappserver.service.IngredientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class IngredientController {

    private IngredientService ingredientService;
    private HashMap<String, Object> message = new HashMap<>();

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    //Cart Ingredients
    @PostMapping("/cart/{cartId}/ingredients/")
    public ResponseEntity<?> createCartIngredient(@RequestBody Ingredient ingredientObject, @PathVariable Long cartId) {
        Ingredient createdIngredient = ingredientService.createCartIngredient(ingredientObject, cartId);
        if (createdIngredient != null) {
            message.put("message", "Ingredient has been saved to the cart");
            message.put("data", createdIngredient);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Unable to create ingredient");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/cart/{cartId}/ingredients/")
    public ResponseEntity<?> getAllCartIngredients(@PathVariable Long cartId){
        List<Ingredient> ingredientList = ingredientService.getAllCartIngredients(cartId);
        if (ingredientList != null) {
            message.put("message", "Shopping list retrieved successfully");
            message.put("data", ingredientList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message","Shopping list is empty");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/cart/{cartId}/ingredients/{ingredientId}/")
    public ResponseEntity<?> deleteCartIngredient(@PathVariable Long cartId,@PathVariable Long ingredientId) {
        Ingredient deletedIngredient = ingredientService.deleteCartIngredient(cartId, ingredientId);
        if (deletedIngredient != null) {
            message.put("message", "Ingredient has been removed from shopping cart");
            message.put("data", deletedIngredient);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Unable to remove ingredient from shopping list");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
