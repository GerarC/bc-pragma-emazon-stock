package com.emazon.stock.domain.utils.pagination;

public class PaginationData {
    private Integer page;
    private String column;
    private boolean ascending;
    private Integer pageSize;

    public PaginationData(Integer page, String column, boolean ascending, Integer pageSize) {
        this.page = page;
        this.column = column;
        this.ascending = ascending;
        this.pageSize = pageSize;
    }

    public PaginationData() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
