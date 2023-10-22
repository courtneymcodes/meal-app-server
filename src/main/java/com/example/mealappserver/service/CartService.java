package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationExistsException;
import com.example.mealappserver.exception.InformationNotFoundException;
import com.example.mealappserver.model.Cart;
import com.example.mealappserver.model.Recipe;
import com.example.mealappserver.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    CartRepository cartRepository;
    UserService userService;

    @Autowired
    public CartService(CartRepository cartRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    public Cart createCart(Cart cartObject) {
        Cart cart = cartRepository.findByNameAndUserId(cartObject.getName(), userService.getCurrentLoggedInUser().getId());
        if (cart == null) {
            cartObject.setUser(userService.getCurrentLoggedInUser());
            return cartRepository.save(cartObject);
        } else {
            throw new InformationExistsException("Cart already exists");
        }
    }

    public Cart getUserCart() {
        Cart cart = cartRepository.findCartByUserId(userService.getCurrentLoggedInUser().getId());
        if (cart != null){
            return cart;
        } else {
            throw new InformationNotFoundException("Cart not found");
        }
    }

}
