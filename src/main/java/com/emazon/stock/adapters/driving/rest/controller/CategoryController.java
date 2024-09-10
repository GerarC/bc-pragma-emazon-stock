package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.service.CategoryService;
import com.emazon.stock.adapters.driving.rest.utils.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = RestConstants.SWAGGER_ADD_CATEGORY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_CREATED, description = RestConstants.SWAGGER_ADD_CATEGORY_RESPONSE, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT, description = RestConstants.SWAGGER_ADD_CATEGORY_ALREADY_EXISTS, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST, description = RestConstants.SWAGGER_ADD_CATEGORY_LONG_NAME, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST, description = RestConstants.SWAGGER_ADD_CATEGORY_LONG_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST, description = RestConstants.SWAGGER_VALIDATIONS_DONT_PASS, content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.save(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = RestConstants.SWAGGER_GET_CATEGORY_BY_ID_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_GET_CATEGORY_BY_ID_FOUND,
                    content = @Content(mediaType = RestConstants.SWAGGER_JSON,
                            schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND, description = RestConstants.SWAGGER_GET_CATEGORY_BY_ID_NOT_FOUND, content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @Operation(summary = RestConstants.SWAGGER_GET_ALL_CATEGORIES_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = RestConstants.SWAGGER_GET_ALL_CATEGORIES_RESPONSE, content = @Content(mediaType = RestConstants.SWAGGER_JSON)),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT', 'CUSTOMER')")
    public ResponseEntity<PageResponse<CategoryResponse>> getAll(@RequestParam Map<String, String> query) {
        PageResponse<CategoryResponse> foundCategories;
        PaginationRequest paginationRequest = new PaginationRequest(query);
        foundCategories = categoryService.getAllCategories(paginationRequest);
        return ResponseEntity.ok(foundCategories);
    }
}
