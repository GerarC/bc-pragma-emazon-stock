package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.CategoryRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.CategoryResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.service.CategoryService;
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

    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category added", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Category name is too long", content = @Content),
            @ApiResponse(responseCode = "400", description = "Category description is too long", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.save(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a category searching by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Category found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @Operation(summary = "Gets all categories, they are paged, if you want it, you can sor by name or description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of the found categories", content = @Content(mediaType = "application/json")),
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
