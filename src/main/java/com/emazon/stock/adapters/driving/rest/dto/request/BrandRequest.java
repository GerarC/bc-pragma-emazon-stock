package com.emazon.stock.adapters.driving.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {
    //Attributes
    @NotNull(message = "Name cannot be null")
    @Size(max=50, message = "Name must be shorter than 50 chars")
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(max=120, message = "Description must be shorter than 120 chars")
    private String description;
}
