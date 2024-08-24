package com.emazon.stock.adapters.driven.jpa.utils;

import com.emazon.stock.domain.utils.DomainConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * A class used only to manage pages in JPA Queries
 */
public class PageUtil {

    private PageUtil() {

    }
    /**
     * Return a pageable object to use in a query
     *
     * @param paginationJPA A pagination Jpa object to manage pagination creation
     * @return Return the prepared pageable
     */
    public static Pageable createPageable(PaginationJPA paginationJPA) {
        Pageable pageable;
        if (paginationJPA.getColumn() == null || paginationJPA.getColumn().isEmpty()) {
            pageable = PageRequest.of(paginationJPA.getPage(), DomainConstants.PAGE_SIZE);
        } else {
            Sort.Direction direction = paginationJPA.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, paginationJPA.getColumn());
            pageable = PageRequest.of(paginationJPA.getPage(), DomainConstants.PAGE_SIZE).withSort(sort);
        }
        return pageable;
    }
}
