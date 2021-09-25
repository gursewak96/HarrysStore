package com.harry.store.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {

    private int id;

    @NotNull
    @Size(min=2, max = 20,message = "Name must be within size: 2 - 20")
    private String name;

    @NotNull(message = "Price cannot be null")
    private double price;
    private double weight;

    @NotNull
    @NotEmpty(message = "Description should not be empty")
    private String description;
    private String imageName;
    private long categoryId;
    private String categoryName;

    public ProductDto(int id, String name,
                      double price, double weight,
                      String description, String imageName,
                      long categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.description = description;
        this.imageName = imageName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public ProductDto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long category_id) {
        this.categoryId = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
