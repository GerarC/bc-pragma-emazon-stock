package com.emazon.stock.domain.model;

import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.NegativeNotAllowedException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.utils.DomainConstants;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private List<Category> categories;
    private Brand brand;

    // Constructors

    public Product(Long id, String name, String description, BigDecimal price, Long quantity, List<Category> categories, Brand brand) {
        if (name.trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if (description.trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeNotAllowedException(DomainConstants.Field.PRICE.toString());
        if (quantity < 0) throw new NegativeNotAllowedException(DomainConstants.Field.QUANTITY.toString());
        if (categories.isEmpty()) throw new EmptyFieldException(DomainConstants.Field.CATEGORIES.toString());
        if (categories.size() > DomainConstants.FIELD_CATEGORIES_LIMIT)
            throw new OutOfBoundsException(DomainConstants.Field.CATEGORIES.toString());


        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.price = requireNonNull(price, DomainConstants.FIELD_PRICE_NULL_MESSAGE);
        this.quantity = requireNonNull(quantity, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.categories = requireNonNull(categories, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.brand = requireNonNull(brand, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Product() {
    }

    // Getters & Setters

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
