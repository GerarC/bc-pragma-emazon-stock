package com.emazon.stock.adapters.driven.jpa.utils;

import com.emazon.stock.domain.utils.DomainConstants;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationJPA {
    private Integer page;
    private String column;
    private boolean ascending;
    private Integer pageSize;

    /**
     * Return a pageable object to use in a query, the options are the attributes of the PaginationJPA instance
     *
     * @return Return the prepared pageable
     */
    public Pageable createPageable() {
        Pageable pageable;
        if (this.getColumn() == null || this.getColumn().isEmpty()) {
            pageable = PageRequest.of(this.getPage(), this.pageSize);
        } else {
            Sort.Direction direction = this.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, this.getColumn());
            pageable = PageRequest.of(this.getPage(), this.pageSize).withSort(sort);
        }
        return pageable;
    }
}
