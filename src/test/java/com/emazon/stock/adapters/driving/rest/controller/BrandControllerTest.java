package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.rest.dto.request.BrandRequest;
import com.emazon.stock.adapters.driving.rest.dto.request.PaginationRequest;
import com.emazon.stock.adapters.driving.rest.dto.response.BrandResponse;
import com.emazon.stock.adapters.driving.rest.dto.response.ResponsePage;
import com.emazon.stock.adapters.driving.rest.service.BrandService;
import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityAlreadyExistsException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.adapters.driving.rest.utils.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandController.class)
@AutoConfigureMockMvc
class BrandControllerTest {


    MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @Autowired
    BrandControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void createBrandIsOK() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("nothing").description("Nothing is a brand associated with nothing").build();

        this.mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isCreated());
        verify(brandService, times(1)).save(brandMock);
    }

    @Test
    void createBrandIsRepeated() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("nothing").description("Another brand with the same name").build();

        doThrow(new EntityAlreadyExistsException(Brand.class.getSimpleName(), "nothing")).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isConflict());
    }

    @Test
    void createBrandNameTooLarge() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").description("brand with a name with more than 50 chars").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.NAME.toString(), String.valueOf(DomainConstants.NAME_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBrandDescriptionTooLarge() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("long description").description("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.DESCRIPTION.toString(), String.valueOf(DomainConstants.CATEGORY_DESCRIPTION_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBrandNoName() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("").description("Nothing is a brand associated with nothing").build();

        doThrow(new EmptyFieldException("NAME")).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBrandNoDescription() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("nothing").description("").build();

        doThrow(new EmptyFieldException("DESCRIPTION")).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAll() throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(0, null, true);
        ResponsePage<BrandResponse> mockDTOs = new ResponsePage<>();
        mockDTOs.setContent(List.of(
                new BrandResponse(1L, "nothing", "description"),
                new BrandResponse(2L, "something", "second description")
        ));
        when(brandService.getAllBrands(paginationRequest)).thenReturn(mockDTOs);
        this.mockMvc.perform(get("/brands"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllWithParams() throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(0, "name", true);
        ResponsePage<BrandResponse> mockDTOs = new ResponsePage<>();
        mockDTOs.setContent(List.of(
                new BrandResponse(1L, "nothing", "description"),
                new BrandResponse(2L, "something", "second description")
        ));
        when(brandService.getAllBrands(paginationRequest)).thenReturn(mockDTOs);
        this.mockMvc.perform(get("/brands")
                        .queryParam("page", "0")
                        .queryParam("sortBy", "name")
                        .queryParam("asc", "true"))
                .andExpect(status().isOk());
    }
}