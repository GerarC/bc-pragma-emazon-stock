package com.emazon.stock.adapters.driving.rest.v1.utils;

public class RestConstants {
    private RestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CODE_OK = "200";
    public static final String CODE_CREATED = "201";
    public static final String CODE_ACCEPTED = "202";
    public static final String CODE_CONFLICT = "409";
    public static final String CODE_BAD_REQUEST = "400";
    public static final String CODE_NOT_FOUND = "404";
    public static final String SWAGGER_JSON = "application/json";


    public static final String SWAGGER_VALIDATIONS_DONT_PASS = "Some of the field doesn't pass validations";

    public static final String SWAGGER_AUTHORIZATION_RESPONSE = "An object with the email, rol and a boolean if it's validated";


    public static final String SWAGGER_ADD_BRAND_SUMMARY = "Add a new brand";
    public static final String SWAGGER_ADD_BRAND_RESPONSE = "Brand added";
    public static final String SWAGGER_ADD_BRAND_ALREADY_EXISTS = "Brand already exists";
    public static final String SWAGGER_ADD_BRAND_LONG_NAME = "Brand name is too long";
    public static final String SWAGGER_ADD_BRAND_LONG_DESCRIPTION = "Brand description is too long";

    public static final String SWAGGER_GET_ALL_BRANDS_SUMMARY = "Gets all brands, they are paged, if you want it, you can sort by name or description";
    public static final String SWAGGER_GET_ALL_BRANDS_RESPONSE = "A list of the found brands";

    public static final String SWAGGER_ADD_CATEGORY_SUMMARY = "Add a new category";
    public static final String SWAGGER_ADD_CATEGORY_RESPONSE = "Category added";
    public static final String SWAGGER_ADD_CATEGORY_ALREADY_EXISTS = "Category already exists";
    public static final String SWAGGER_ADD_CATEGORY_LONG_NAME = "Category name is too long";
    public static final String SWAGGER_ADD_CATEGORY_LONG_DESCRIPTION = "Category description is too long";

    public static final String SWAGGER_GET_CATEGORY_BY_ID_SUMMARY = "Get a category searching by its id";
    public static final String SWAGGER_GET_CATEGORY_BY_ID_FOUND = "Category found";
    public static final String SWAGGER_GET_CATEGORY_BY_ID_NOT_FOUND = "Category not found";

    public static final String SWAGGER_GET_ALL_CATEGORIES_SUMMARY = "Gets all categories, they are paged, if you want it, you can sor by name or description";
    public static final String SWAGGER_GET_ALL_CATEGORIES_RESPONSE = "A page with all found categories";

    public static final String SWAGGER_ADD_PRODUCT_SUMMARY = "Adds a new product with its brand and categories";
    public static final String SWAGGER_ADD_PRODUCT_RESPONSE = "Product added";
    public static final String SWAGGER_ADD_PRODUCT_BRAND_NOT_EXISTS = "Associated brand doesn't exist";
    public static final String SWAGGER_ADD_PRODUCT_DUPLICATED_CATEGORIES = "Some categories of the product are duplicated";
    public static final String SWAGGER_ADD_PRODUCT_CATEGORY_NOT_FOUND = "One of the associated categories don't exist";
    public static final String SWAGGER_ADD_PRODUCT_LONG_NAME = "Product name is too long";
    public static final String SWAGGER_ADD_PRODUCT_LONG_DESCRIPTION = "Product description is too long";

    public static final String SWAGGER_GET_ALL_PRODUCTS_SUMMARY = "Gets all products, they are paged, if you want it, you can sort by any thing";
    public static final String SWAGGER_GET_ALL_PRODUCTS_RESPONSE = "A page of the found categories";
    public static final String SWAGGER_GET_PRODUCT_CATEGORY_SUMMARY = "Get all categories of a product";
    public static final String SWAGGER_GET_PRODUCT_CATEGORY_RESPONSE = "A bunch of categories related with the found product";
    public static final String SWAGGER_PRODUCT_NOT_FOUND = "Product not found";

    public static final String SWAGGER_ADD_SUPPLIES_SUMMARY = "Add the given quantity to the product";
    public static final String SWAGGER_ADD_SUPPLIES_RESPONSE = "That supply has been added to the product quantity";

    public static final String SWAGGER_GET_PRODUCT_SUMMARY = "Get a product by its id";
    public static final String SWAGGER_GET_PRODUCT_RESPONSE = "Product found";

    public static final String SWAGGER_GET_PRODUCTS_SUMMARY = "Gets a list of products by their ids";
    public static final String SWAGGER_GET_PRODUCTS_RESPONSE = "Found products";
    public static final String SWAGGER_GET_PRODUCTS_NOT_FOUND = "At least one of the products hasn't been found";


}
