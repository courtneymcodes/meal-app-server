package com.example.mealappserver.controller;

import com.example.mealappserver.exception.InformationNotFoundException;
import com.example.mealappserver.model.Cart;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class CartController {

    CartService cartService;
    public HashMap<String, Object> message = new HashMap<>();

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(path = "/cart/")
    public ResponseEntity<?> createCart(@RequestBody Cart cartObject) {
        Cart cart = cartService.createCart(cartObject);
        if(cart != null){
            message.put("message", "Cart created");
            message.put("data", cart);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Unable to create cart");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
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
