package com.emazon.stock.adapters.driving.rest.v1.dto.request;

import com.emazon.stock.domain.utils.DomainConstants;
import jakarta.annotation.Nullable;
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

    public PaginationRequest(@Nullable Map<String, String> query) {
        this.column = null;
        this.page = 0;
        this.ascending = true;
        this.pageSize = DomainConstants.PAGE_SIZE;
        if(query == null) return;
        if(query.containsKey("sortBy")) this.column = query.get("sortBy");
        if (query.containsKey("page")) this.page = Integer.parseInt(query.get("page"));
        if (query.containsKey("asc")) this.ascending = Boolean.parseBoolean(query.get("asc"));
        if (query.containsKey("pageSize")) this.pageSize = Integer.parseInt(query.get("pageSize"));
    }

    public PaginationRequest(@Nullable PageQuery query) {
        this.column = null;
        this.page = 0;
        this.ascending = true;
        this.pageSize = DomainConstants.PAGE_SIZE;
        if(query == null) return;
        if(query.getSortBy() != null) this.column = query.getSortBy();
        if (query.getPage() != null) this.page = query.getPage();
        if (query.getAsc() != null) this.ascending = query.getAsc();
        if (query.getPageSize() != null) this.pageSize = query.getPageSize();
    }
}
