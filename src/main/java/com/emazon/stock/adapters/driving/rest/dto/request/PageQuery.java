package com.emazon.stock.adapters.driving.rest.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageQuery {
    private String sortBy;
    private Integer page;
    private Boolean asc;
    private Integer pageSize;
}
