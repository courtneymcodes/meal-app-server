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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getItems() {
        return items;
    }

    public void setItems(List<Ingredient> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
