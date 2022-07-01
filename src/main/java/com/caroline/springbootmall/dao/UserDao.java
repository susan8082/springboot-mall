package com.caroline.springbootmall.dao;

import com.caroline.springbootmall.dto.UserRequestDto;
import com.caroline.springbootmall.model.User;

public interface UserDao {

     User createUser(UserRequestDto dto);
     User getUserByEmail(String email);
}
