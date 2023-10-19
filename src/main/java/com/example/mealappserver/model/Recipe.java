package com.example.mealappserver.model;

public class Recipe {
    private Long id;

    private String name;

    private String ingredients;

    private String instructions;

    private String imageUrl;

    private String sourceUrl;

    public Recipe() {
    }

    public Recipe(String name, String ingredients, String instructions, String imageUrl, String sourceUrl) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
    }


}
