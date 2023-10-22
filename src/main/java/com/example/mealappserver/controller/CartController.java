package com.example.mealappserver.controller;

import com.example.mealappserver.model.Cart;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class CartController {

    CartService cartService;
    public HashMap<String, Object> message = new HashMap<>();
    @GetMapping(path = "/cart/")
    public ResponseEntity<?> getUserCart() {
            Cart cart = cartService.getUserCart();
            if (cart != null) {
                message.put("message", "Cart Retrieved");
                message.put("data", cart);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.put("message", "Cart not found");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        }
}
