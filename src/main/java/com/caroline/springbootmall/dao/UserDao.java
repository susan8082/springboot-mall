package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.dto.UserRegisterRequestDto;
import com.caroline.springbootmall.model.User;

public interface UserDao {

     User createUser(User user);
     User getUserByEmail(String email);
}
