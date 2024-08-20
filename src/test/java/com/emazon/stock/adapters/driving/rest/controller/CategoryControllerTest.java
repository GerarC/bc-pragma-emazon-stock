package com.emazon.stock.adapters.driving.rest.controller;

import com.emazon.stock.adapters.driving.dto.CategoryDTO;
import com.emazon.stock.adapters.driving.service.CategoryService;
import com.emazon.stock.domain.exceptions.CategoryAlreadyExistsException;
import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import com.emazon.stock.domain.utils.DomainConstants;
import com.emazon.stock.utils.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {


    MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    CategoryControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void createCategoryIsOK() throws Exception {
        CategoryDTO categoryMock = CategoryDTO.builder().name("nothing").description("Nothing is a category associated with nothing").build();

        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)) )
                .andExpect(status().isCreated());
        verify(categoryService, times(1)).save(categoryMock);
    }

    @Test
    void createCategoryIsRepeated() throws Exception {
        CategoryDTO categoryMock = CategoryDTO.builder().name("nothing").description("Another category with the same name").build();

        doThrow(new CategoryAlreadyExistsException("nothing")).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)) )
                .andExpect(status().isConflict());
    }

    @Test
    void createCategoryNameTooLarge() throws Exception {
        CategoryDTO categoryMock = CategoryDTO.builder().name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").description("category with a name with more than 50 chars").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.NAME.toString(), String.valueOf(DomainConstants.CATEGORY_NAME_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)) )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryDescriptionTooLarge() throws Exception {
        CategoryDTO categoryMock = CategoryDTO.builder().name("long description").description("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.DESCRIPTION.toString(), String.valueOf(DomainConstants.CATEGORY_DESCRIPTION_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryNoName() throws Exception {
        CategoryDTO categoryMock = CategoryDTO.builder().name("").description("Nothing is a category associated with nothing").build();

        doThrow(new EmptyFieldException("NAME")).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryNoDescription() throws Exception {
        CategoryDTO categoryMock = CategoryDTO.builder().name("nothing").description("").build();

        doThrow(new EmptyFieldException("DESCRIPTION")).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCategoryById() throws Exception {
        CategoryDTO categoryMock = CategoryDTO.builder().id(1L).name("nothing").description("Nothing is a category associated with nothing").build();
        when(categoryService.getCategory(1L)).thenReturn(categoryMock);
        when(categoryService.getCategory(2L)).thenThrow(new EntityNotFoundException("Category with id 4 not found"));
        this.mockMvc.perform(get("/categories/1"))
                .andExpect(content().json(JsonParser.toJson(categoryMock)))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/categories/2"))
                .andExpect(status().isNotFound());

    }
}