package com.emazon.stock.adapters.driving.rest.dto.request;

import com.emazon.stock.domain.utils.DomainConstants;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationRequest {
    private Integer page;
    private String column;
    private boolean ascending;
    private Integer pageSize;

    public PaginationRequest(Map<String, String> query) {
        String sortBy = null;
        int pageNum = 0;
        boolean asc = true;
        int size = DomainConstants.PAGE_SIZE;
        if(query.containsKey("sortBy")) sortBy = query.get("sortBy");
        if (query.containsKey("page")) pageNum = Integer.parseInt(query.get("page"));
        if (query.containsKey("asc")) asc = Boolean.parseBoolean(query.get("asc"));
        if (query.containsKey("pageSize")) size = Integer.parseInt(query.get("pageSize"));
        this.page = pageNum;
        this.column = sortBy;
        this.ascending = asc;
        this.pageSize = size;
    }
}
