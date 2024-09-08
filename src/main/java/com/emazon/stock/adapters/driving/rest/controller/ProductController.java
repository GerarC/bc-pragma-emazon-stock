package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductResponse;
import com.emazon.stock.adapters.driving.rest.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Adds a new product with its brand and categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product added", content = @Content),
            @ApiResponse(responseCode = "404", description = "Associated brand doesn't exist", content = @Content),
            @ApiResponse(responseCode = "409", description = "Some categories of the product are duplicated", content = @Content),
            @ApiResponse(responseCode = "404", description = "One of the associated categories don't exist", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some of the required attributes is null", content = @Content),
            @ApiResponse(responseCode = "400", description = "Product name is too long", content = @Content),
            @ApiResponse(responseCode = "400", description = "Product description is too long", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Gets all products, they are paged, if you want it, you can sort by any thing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A page of the found categories", content = @Content(mediaType = "application/json", schema =  @Schema(implementation = PageResponse.class))),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT', 'CUSTOMER')")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(@RequestParam Map<String, String> query) {
        PaginationRequest paginationRequest = new PaginationRequest(query);
        return ResponseEntity.ok(productService.getAllProducts(paginationRequest));
    }


    @Operation(summary = "Get all categories of a product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductCategoryResponse.class)))
            ),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @GetMapping("/{id}/categories")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT', 'CUSTOMER')")
    public ResponseEntity<List<ProductCategoryResponse>> getProductCategories(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductCategories(id));
    }

    @PostMapping("/{id}/add-supply")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT')")
    public ResponseEntity<ProductResponse> addSupplies(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        productService.addSupply(id, productRequest);
        return ResponseEntity.ok().build();
    }
}
