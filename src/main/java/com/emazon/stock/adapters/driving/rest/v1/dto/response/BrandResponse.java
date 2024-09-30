package com.emazon.stock.adapters.driving.rest.v1.dto.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    //Attributes
    private Long id;
    private String name;
    private String description;
}
