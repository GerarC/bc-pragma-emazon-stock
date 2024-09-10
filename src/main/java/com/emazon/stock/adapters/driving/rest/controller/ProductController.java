package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.ProductRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductCategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ProductResponse;
import com.emazon.stock.adapters.driving.rest.service.ProductService;
import com.emazon.stock.adapters.driving.rest.utils.RestConstants;
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

    @Operation(summary = RestConstants.SWAGGER_ADD_PRODUCT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_CREATED, description = RestConstants.SWAGGER_ADD_PRODUCT_RESPONSE, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT, description = RestConstants.SWAGGER_ADD_PRODUCT_BRAND_NOT_EXISTS, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT, description = RestConstants.SWAGGER_ADD_PRODUCT_DUPLICATED_CATEGORIES, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND, description = RestConstants.SWAGGER_ADD_PRODUCT_CATEGORY_NOT_FOUND, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT, description = RestConstants.SWAGGER_ADD_PRODUCT_LONG_NAME, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT, description = RestConstants.SWAGGER_ADD_PRODUCT_LONG_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST, description = RestConstants.SWAGGER_VALIDATIONS_DONT_PASS, content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = RestConstants.SWAGGER_GET_ALL_PRODUCTS_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = RestConstants.SWAGGER_GET_ALL_PRODUCTS_RESPONSE, content = @Content(mediaType = RestConstants.SWAGGER_JSON, schema =  @Schema(implementation = PageResponse.class))),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT', 'CUSTOMER')")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(@RequestParam Map<String, String> query) {
        PaginationRequest paginationRequest = new PaginationRequest(query);
        return ResponseEntity.ok(productService.getAllProducts(paginationRequest));
    }


    @Operation(summary = RestConstants.SWAGGER_GET_PRODUCT_CATEGORY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_GET_PRODUCT_CATEGORY_RESPONSE,
                    content = @Content(mediaType = RestConstants.SWAGGER_JSON,
                            array = @ArraySchema(schema = @Schema(implementation = ProductCategoryResponse.class)))
            ),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND, description = RestConstants.SWAGGER_GET_PRODUCT_CATEGORY_NOT_FOUND, content = @Content)
    })
    @GetMapping("/{id}/categories")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT', 'CUSTOMER')")
    public ResponseEntity<List<ProductCategoryResponse>> getProductCategories(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductCategories(id));
    }

    @Operation(summary = RestConstants.SWAGGER_ADD_SUPPLIES_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_ADD_SUPPLIES_RESPONSE,
                    content = @Content(mediaType = RestConstants.SWAGGER_JSON,
                            array = @ArraySchema(schema = @Schema(implementation = ProductCategoryResponse.class)))
            ),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND, description = RestConstants.SWAGGER_GET_PRODUCT_CATEGORY_NOT_FOUND, content = @Content)
    })
    @PutMapping("/{id}/add-supply")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT')")
    public ResponseEntity<ProductResponse> addSupplies(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        productService.addSupply(id, productRequest);
        return ResponseEntity.ok().build();
    }
}
