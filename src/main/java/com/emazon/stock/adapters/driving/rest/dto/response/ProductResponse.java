package com.emazon.stock.adapters.driving.rest.dto.response;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private List<Category> categories;
    private Brand brand;
}
