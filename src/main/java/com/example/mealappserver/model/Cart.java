package com.example.mealappserver.model;

import java.util.List;

public class Cart {

    private String id;

    private String name;

    private List<Ingredient> items;

    public Cart() {
    }

    public Cart(String name, List<Ingredient> items) {
        this.name = name;
        this.items = items;
    }
}
