package com.example.mealappserver.controller;

import com.example.mealappserver.model.Ingredient;
import com.example.mealappserver.service.IngredientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/")
public class IngredientController {

    private IngredientService ingredientService;
    private HashMap<String, Object> message = new HashMap<>();

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

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
}
