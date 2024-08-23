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

    // NULL Messages
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_PRICE_NULL_MESSAGE = "Field 'price' cannot be null";
    public static final String FIELD_QUANTITY_NULL_MESSAGE = "Field 'quantity' cannot be null";
    public static final String FIELD_CATEGORIES_NULL_MESSAGE = "Field 'categories' cannot be null";
    public static final String FIELD_PRODUCTS_NULL_MESSAGE = "Field 'products' cannot be null";
    public static final String FIELD_PRODUCT_NULL_MESSAGE = "Field 'product' cannot be null";
    public static final String FIELD_BRAND_NULL_MESSAGE = "Field 'brand' cannot be null";
    public static final String NOT_FOUND_MESSAGE = "not found";

    // bound limits

    public static final Integer FIELD_CATEGORIES_LIMIT = 3;
    public static final Integer NAME_LENGTH_LIMIT = 50;
    public static final Integer CATEGORY_DESCRIPTION_LENGTH_LIMIT = 90;
    public static final Integer BRAND_DESCRIPTION_LENGTH_LIMIT = 120;

    // Out of bounds
    public static final String CHARS_LIMIT_REACHED_MESSAGE = "chars limit reached";
    public static final String CATEGORIES_LIMIT_REACHED_MESSAGE = "categories limit reached";

    // Pages
    public static final Integer PAGE_SIZE = 10;

}
