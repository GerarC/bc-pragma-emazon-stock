package com.emazon.stock.adapters.driven.jpa.utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationJPA {
    private int page;
    private String column;
    private boolean ascending;
}
