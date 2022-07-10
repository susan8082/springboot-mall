package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dto.BuyItem;
import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.dto.UserRegisterRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductDao productDao;

    @BeforeAll
    void builtUser() throws Exception {
        register("test1@gmail.com", "123");
    }

    @Test
    @Transactional
    public void createOrder_success() throws Exception {
        //create order
        OrderCreateRequestDto mockOrder = new OrderCreateRequestDto();
        mockOrder.setBuyItemList(Arrays.asList(builtBuyItem(1,2), builtBuyItem(2,2)));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.orderId", notNullValue()))
                .andExpect(jsonPath("$.totalAmount", equalTo(30*2+300*2)))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()))
                .andExpect(jsonPath("$.userId", equalTo(1)))
                .andExpect(jsonPath("$.orderItems", hasSize(2)))
                .andExpect(jsonPath("$.orderItems[0].orderItemId", notNullValue()))
                .andExpect(jsonPath("$.orderItems[0].orderId", notNullValue()))
                .andExpect(jsonPath("$.orderItems[0].productId", equalTo(1)))
                .andExpect(jsonPath("$.orderItems[0].productName", notNullValue()))
                .andExpect(jsonPath("$.orderItems[0].imageUrl", notNullValue()))
                .andExpect(jsonPath("$.orderItems[0].quantity", equalTo(2)))
                .andExpect(jsonPath("$.orderItems[0].amount", equalTo(30*2)))
                .andExpect(status().isCreated());

        //check stock
        Product product1 = productDao.getProductById(1);
        Product product2 = productDao.getProductById(2);
        assertEquals(product1.getStock(), 10-2);
        assertEquals(product2.getStock(), 5-2);

    }

    @Test
    @Transactional
    void createOrder_productNotFound() throws Exception {
        //create order
        OrderCreateRequestDto mockOrder = new OrderCreateRequestDto();
        mockOrder.setBuyItemList(Arrays.asList(builtBuyItem(10000,2)));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void createOrder_buyItemListEmpty() throws Exception {
        //create order
        OrderCreateRequestDto mockOrder = new OrderCreateRequestDto();
        mockOrder.setBuyItemList(Arrays.asList());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void createOrder_userNotFound() throws Exception {
        //create order
        OrderCreateRequestDto mockOrder = new OrderCreateRequestDto();
        mockOrder.setBuyItemList(Arrays.asList(builtBuyItem(1,2)));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/{userId}/orders", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void createOrder_stockNotEnough() throws Exception {
        //create order
        OrderCreateRequestDto mockOrder = new OrderCreateRequestDto();
        mockOrder.setBuyItemList(Arrays.asList(builtBuyItem(1,200000)));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/{userId}/orders", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void getUserOrders_success() throws Exception {
        //createOrders
        List<BuyItem> buyItemLists = Arrays.asList(builtBuyItem(1,2));
        createOrder(1, buyItemLists);
        createOrder(1, buyItemLists);

        //getOrders
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/{userId}/orders", 1);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.totalCount", equalTo(2)))
                .andExpect(jsonPath("$.results", hasSize(2)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void getUserOrders_userNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/{userId}/orders", 2);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void getUserOrders_noOrders() throws Exception {
        //getOrders
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/{userId}/orders", 1);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.totalCount", equalTo(0)))
                .andExpect(jsonPath("$.results", hasSize(0)))
                .andExpect(status().isOk());
    }


    private void register(String email, String password) throws Exception {
        UserRegisterRequestDto userRegisterRequest = new UserRegisterRequestDto();
        userRegisterRequest.setEmail(email);
        userRegisterRequest.setPassword(password);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilderRegister = MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilderRegister)
                .andDo(print())
                .andExpect(status().isCreated());

    }

    private void createOrder(Integer userId, List<BuyItem> buyItemList) throws Exception {
        OrderCreateRequestDto mockOrder = new OrderCreateRequestDto();
        mockOrder.setBuyItemList(buyItemList);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/{userId}/orders", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private BuyItem builtBuyItem(Integer productId, Integer quantity){
        BuyItem buyItem = new BuyItem();
        buyItem.setProductId(productId);
        buyItem.setQuantity(quantity);
        return buyItem;
    }
}