package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductResponse;
import com.emazon.stock.adapters.driving.rest.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Adds a new product with its brand and categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product added", content = @Content),
            @ApiResponse(responseCode = "404", description = "Associated brand doesn't exist", content = @Content),
            @ApiResponse(responseCode = "409", description = "Some categories are duplicated", content = @Content),
            @ApiResponse(responseCode = "404", description = "One of the associated categories don't exist", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some of the required attributes is null", content = @Content),
            @ApiResponse(responseCode = "400", description = "Product name is too long", content = @Content),
            @ApiResponse(responseCode = "400", description = "Product description is too long", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
