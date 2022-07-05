package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.constant.ProductCategory;
import com.caroline.springbootmall.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductById_success() throws Exception{
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
    public void getProductById_notFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/{productId}", 10000);
    mockMvc.perform(requestBuilder)
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void createProduct_success() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setProductName("fish");
        mockProduct.setCategory(ProductCategory.FOOD);
        mockProduct.setPrice(100);
        mockProduct.setStock(10);
        mockProduct.setImageUrl("http://test.com");
        mockProduct.setDescription("It's a fish.");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.productId", notNullValue()))
                .andExpect(jsonPath("$.category", equalTo("FOOD")))
                .andExpect(jsonPath("$.productName", notNullValue()))
                .andExpect(jsonPath("$.price", notNullValue()))
                .andExpect(jsonPath("$.stock", notNullValue()))
                .andExpect(jsonPath("$.imageUrl", notNullValue()))
                .andExpect(jsonPath("$.description", notNullValue()))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()))

                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void createProduct_ilegalArgument() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setProductName("fish");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void updateProduct_success() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setProductName("fish");
        mockProduct.setCategory(ProductCategory.FOOD);
        mockProduct.setPrice(100);
        mockProduct.setStock(10);
        mockProduct.setImageUrl("http://test.com");
        mockProduct.setDescription("It's a fish.");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/products/{productId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.productId", equalTo(1)))
                .andExpect(jsonPath("$.productName", equalTo("fish")))
                .andExpect(jsonPath("$.category", equalTo("FOOD")))
                .andExpect(jsonPath("$.price", equalTo(100)))
                .andExpect(jsonPath("$.imageUrl", equalTo("http://test.com")))
                .andExpect(jsonPath("$.description", equalTo("It's a fish.")))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateProduct_ilegalArgument() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setProductName("fish");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/products/{productId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void updateProduct_productNotFound() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setProductName("fish");
        mockProduct.setCategory(ProductCategory.FOOD);
        mockProduct.setPrice(100);
        mockProduct.setStock(10);
        mockProduct.setImageUrl("http://test.com");
        mockProduct.setDescription("It's a fish.");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/products/{productId}", 100000)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void deleteProduct_success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/products/{productId}", 1);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void deleteProduct_notExistingProduct() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/products/{productId}", 500000);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllProducts_success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.total", equalTo(7)))
                .andExpect(jsonPath("$.products",hasSize(5)))
                .andExpect(jsonPath("$.size",equalTo(5)))
                .andExpect(jsonPath("$.pageIndex",equalTo(0)))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void getAllProducts_filter() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/products/")
                .param("search", "B")
                .param("category", "CAR");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.total", equalTo(2)))
                .andExpect(jsonPath("$.products", hasSize(2)))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void getAllProducts_sort() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/products/")
                .param("orderBy", "price")
                .param("descOrAsc", "asc");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.total", equalTo(7)))
                .andExpect(jsonPath("$.products",hasSize(5)))
                .andExpect(jsonPath("$.products[0].productId",equalTo(3)))
                .andExpect(jsonPath("$.products[1].productId",equalTo(1)))
                .andExpect(jsonPath("$.products[2].productId",equalTo(2)))
                .andExpect(jsonPath("$.products[3].productId",equalTo(4)))
                .andExpect(jsonPath("$.products[4].productId",equalTo(7)))

                .andExpect(status().isOk()
                );
    }

    @Test
    public void getAllProducts_paging() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/products/")
                .param("pageIndex","1")
                .param("size","3");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.total", equalTo(7)))
                .andExpect(jsonPath("$.pageIndex", equalTo(1)))
                .andExpect(jsonPath("$.products", hasSize(3)))
                .andExpect(jsonPath("$.products[0].productId",equalTo(4)))
                .andExpect(jsonPath("$.products[1].productId",equalTo(2)))
                .andExpect(jsonPath("$.products[2].productId",equalTo(1)))
                .andExpect(status().isOk()
                );
    }
}