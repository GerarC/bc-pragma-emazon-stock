package com.emazon.stock.adapters.driving.rest.v1.dto.request;

import com.emazon.stock.domain.utils.DomainConstants;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryRequest {
    @NotNull(message = DomainConstants.FIELD_ID_NULL_MESSAGE)
    private Long id;
}
