package com.emazon.stock.adapters.driving.rest.dto.request.filter;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterRequest {
    private List<Long> ids;
    private String name;
    private String category;
    private String brand;
}
