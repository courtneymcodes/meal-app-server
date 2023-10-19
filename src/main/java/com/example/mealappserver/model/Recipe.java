package com.example.mealappserver.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }
}
