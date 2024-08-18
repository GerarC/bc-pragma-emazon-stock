package com.emazon.stock.domain.utils;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
        PRICE,
        QUANTITY,
        CATEGORIES,
        PRODUCTS,
        PRODUCT,
        BRAND
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_PRICE_NULL_MESSAGE = "Field 'price' cannot be null";
    public static final String FIELD_QUANTITY_NULL_MESSAGE = "Field 'quantity' cannot be null";
    public static final String FIELD_CATEGORIES_NULL_MESSAGE = "Field 'categories' cannot be null";
    public static final String FIELD_PRODUCTS_NULL_MESSAGE = "Field 'products' cannot be null";
    public static final String FIELD_PRODUCT_NULL_MESSAGE = "Field 'product' cannot be null";
    public static final String FIELD_BRAND_NULL_MESSAGE = "Field 'brand' cannot be null";
}
