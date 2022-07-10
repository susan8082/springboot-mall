package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dto.UserLoginRequestDto;
import com.caroline.springbootmall.dto.UserRegisterRequestDto;
import com.caroline.springbootmall.model.User;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void register_success() throws Exception {

        UserRegisterRequestDto userRegisterRequest = new UserRegisterRequestDto();
        userRegisterRequest.setEmail("test1@gmail.com");
        userRegisterRequest.setPassword("123");
        userRegisterRequest.setUserName("Caroline");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", notNullValue()))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedTime", notNullValue()))
                .andExpect(jsonPath("$.userName", notNullValue()))
                .andExpect(status().isCreated());

        //check Password in DB is hashed
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        assertNotEquals(user.getPassword(), userRegisterRequest.getPassword());

    }

    @Test
    @Transactional
    public void register_invalidEmailFormat() throws Exception {

        UserRegisterRequestDto userRegisterRequest = new UserRegisterRequestDto();
        userRegisterRequest.setEmail("test2");
        userRegisterRequest.setPassword("123");
        userRegisterRequest.setUserName("Caroline");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    public void register_emailAlreadyExist() throws Exception {

        //register an user
        UserRegisterRequestDto userRegisterRequest = new UserRegisterRequestDto();
        userRegisterRequest.setEmail("test3@gmail.com");
        userRegisterRequest.setPassword("123");
        userRegisterRequest.setUserName("Caroline");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        //register again
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    public void login_success() throws Exception {

        // register
        UserRegisterRequestDto userRegisterRequest = new UserRegisterRequestDto();
        userRegisterRequest.setEmail("test4@gmail.com");
        userRegisterRequest.setPassword("123");
        userRegisterRequest.setUserName("Caroline");

        register(userRegisterRequest);

        //login
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRegisterRequest);
        RequestBuilder requestBuilderLogin = MockMvcRequestBuilders.post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilderLogin)
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(userRegisterRequest.getEmail())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedTime", notNullValue()))
                .andExpect(status().isOk());


    }

    @Test
    @Transactional
    public void login_invalidPassword() throws Exception {

        //register
        UserRegisterRequestDto userRegisterRequest = new UserRegisterRequestDto();
        userRegisterRequest.setEmail("test5@gmail.com");
        userRegisterRequest.setPassword("123");
        userRegisterRequest.setUserName("Caroline");

        register(userRegisterRequest);

        //login
        userRegisterRequest.setPassword("456");
        ObjectMapper objectMapper = new ObjectMapper();
        String revisedJson = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilderLogin = MockMvcRequestBuilders.post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON).content(revisedJson);

        mockMvc.perform(requestBuilderLogin)
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    public void login_emailNotFound() throws Exception {

        //login without register
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        userLoginRequestDto.setEmail("test6@gmail.com");
        userLoginRequestDto.setPassword("123");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userLoginRequestDto);

        RequestBuilder requestBuilderLogin = MockMvcRequestBuilders.post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilderLogin)
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    public void login_invalidEmailFormat() throws Exception {

        //login
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        userLoginRequestDto.setEmail("test7");
        userLoginRequestDto.setPassword("123");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userLoginRequestDto);

        RequestBuilder requestBuilderLogin = MockMvcRequestBuilders.post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilderLogin)
                .andExpect(status().isBadRequest());

    }

    private void register(UserRegisterRequestDto userRegisterRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilderRegister = MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(requestBuilderRegister)
                .andExpect(status().isCreated());

    }

}