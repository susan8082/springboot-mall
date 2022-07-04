package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.constant.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProduct_success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/{productId}", 3);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.productId", equalTo(3)))
                .andExpect(jsonPath("$.category", equalTo("FOOD")))
                .andExpect(jsonPath("$.productName", notNullValue()))
                .andExpect(jsonPath("$.imageUrl", notNullValue()))
                .andExpect(jsonPath("$.price", notNullValue()))
                .andExpect(jsonPath("$.stock", notNullValue()))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void getProduct_notFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/{productId}", 10000);
    mockMvc.perform(requestBuilder)
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void getAllProducts_success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.total", equalTo(7)))
                .andExpect(jsonPath("$.products.length()",equalTo(5)))
                .andExpect(status().isOk()
                );
    }
}