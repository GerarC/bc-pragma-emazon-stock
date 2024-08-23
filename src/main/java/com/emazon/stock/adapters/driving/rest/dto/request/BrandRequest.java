package com.emazon.stock.adapters.driving.rest.dto.request;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {
    //Attributes
    private String name;
    private String description;
}
