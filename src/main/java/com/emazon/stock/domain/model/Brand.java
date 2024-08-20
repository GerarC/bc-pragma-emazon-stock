package com.emazon.stock.domain.model;

import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.utils.DomainConstants;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Brand {
    // Attributes

    private Long id;
    private String name;
    private String description;
    private List<Product> products;

    // Constructors

    public Brand(Long id, String name, String description, List<Product> products) {
        this.id = id;
        this.name =  name;
        this.description = description;
        this.products = products;
    }

    public Brand() {
    }

    // Getters and Setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
