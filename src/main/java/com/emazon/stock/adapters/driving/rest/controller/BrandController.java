package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "Add a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand added", content = @Content),
            @ApiResponse(responseCode = "409", description = "Brand already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Brand name is too long", content = @Content),
            @ApiResponse(responseCode = "400", description = "Brand description is too long", content = @Content)
    })
    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest) {
        brandService.save(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Gets all brands, they are paged, if you want it, you can sort by name or description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of the found brands", content = @Content),
    })
    @GetMapping
    public ResponseEntity<PageResponse<BrandResponse>> getAll(@RequestParam Map<String, String> query) {
        PageResponse<BrandResponse> foundBrands;
        PaginationRequest paginationRequest = new PaginationRequest(query);
        foundBrands = brandService.getAllBrands(paginationRequest);
        return ResponseEntity.ok(foundBrands);
    }
}
