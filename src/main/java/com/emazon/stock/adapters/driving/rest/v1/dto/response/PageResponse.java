package com.emazon.stock.adapters.driving.rest.v1.dto.response;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> {
    Integer page;
    Integer pageSize;
    Integer totalPages;
    Integer count;
    Long totalCount;
    List<T> content;
}
