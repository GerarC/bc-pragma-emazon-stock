package com.emazon.stock.adapters.driven.jpa.mapper;


import com.emazon.stock.adapters.driven.jpa.utils.PaginationJPA;
import com.emazon.stock.domain.utils.pagination.PaginationData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationJPAMapper {
    PaginationJPA toJPA(PaginationData paginationData);
}
