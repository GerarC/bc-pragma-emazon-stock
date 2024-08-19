package com.emazon.stock.application.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    //Attributes
    private Long id;
    private String name;
    private String description;
}
