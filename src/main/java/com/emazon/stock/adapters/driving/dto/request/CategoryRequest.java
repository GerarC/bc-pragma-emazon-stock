package com.emazon.stock.adapters.driving.dto.request;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    //Attributes
    private String name;
    private String description;
}
