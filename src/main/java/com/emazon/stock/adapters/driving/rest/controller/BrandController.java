package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PageQuery;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.stock.adapters.driving.rest.service.BrandService;
import com.emazon.stock.adapters.driving.rest.utils.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = RestConstants.SWAGGER_ADD_BRAND_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = RestConstants.SWAGGER_ADD_BRAND_RESPONSE, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT, description = RestConstants.SWAGGER_ADD_BRAND_ALREADY_EXISTS, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST, description = RestConstants.SWAGGER_ADD_BRAND_LONG_NAME, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST, description = RestConstants.SWAGGER_ADD_BRAND_LONG_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST, description = RestConstants.SWAGGER_VALIDATIONS_DONT_PASS, content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandRequest brandRequest) {
        brandService.save(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = RestConstants.SWAGGER_GET_ALL_BRANDS_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = RestConstants.SWAGGER_GET_ALL_BRANDS_RESPONSE, content = @Content),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE_ASSISTANT', 'CUSTOMER')")
    public ResponseEntity<PageResponse<BrandResponse>> getAll(@Nullable PageQuery query) {
        PageResponse<BrandResponse> foundBrands;
        PaginationRequest paginationRequest = new PaginationRequest(query);
        foundBrands = brandService.getAllBrands(paginationRequest);
        return ResponseEntity.ok(foundBrands);
    }
}
