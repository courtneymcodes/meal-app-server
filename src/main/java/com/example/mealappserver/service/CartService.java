package com.example.mealappserver.service;

import com.example.mealappserver.exception.InformationNotFoundException;
import com.example.mealappserver.model.Cart;
import com.example.mealappserver.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    CartRepository cartRepository;
    UserService userService;

    public Cart getUserCart() {
        Cart cart = cartRepository.findCartByUserId(userService.getCurrentLoggedInUser().getId());
        if (cart != null){
            return cart;
        } else {
            throw new InformationNotFoundException("Cart not found");
        }
    }

}
