package com.emazon.stock.adapters.driven.jpa.utils;

import com.emazon.stock.domain.utils.DomainConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * A class used only to manage pages in JPA Queries
 */
public class PageUtil {
    /**
     * Return a pageable object to use in a query
     *
     * @param page which page you want to get
     * @param col what column the page will sort of with
     * @param asc if the sorting mode is ascendant or not
     * @return Return the prepared pageable
     */
    public static Pageable createPageable(int page, String col, boolean asc) {
        Pageable pageable;
        if (col == null || col.isEmpty()) {
            pageable = PageRequest.of(page, DomainConstants.PAGE_SIZE);
        } else {
            Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, col);
            pageable = PageRequest.of(page, DomainConstants.PAGE_SIZE).withSort(sort);
        }
        return pageable;
    }
}
