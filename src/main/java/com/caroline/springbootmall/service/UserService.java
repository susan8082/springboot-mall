package com.caroline.springbootmall.service;

import com.caroline.springbootmall.dto.UserRequestDto;
import com.caroline.springbootmall.model.User;

public interface UserService {
    User createUser (UserRequestDto dto);
}
