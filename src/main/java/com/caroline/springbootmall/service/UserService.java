package com.caroline.springbootmall.service;

import com.caroline.springbootmall.dto.UserLoginRequestDto;
import com.caroline.springbootmall.dto.UserRegisterRequestDto;
import com.caroline.springbootmall.model.User;

public interface UserService {
    User register (UserRegisterRequestDto dto);

    User login(UserLoginRequestDto loginDto);
}
