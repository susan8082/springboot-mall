package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dao.repository.UserRepository;
import com.caroline.springbootmall.dto.UserRequestDto;
import com.caroline.springbootmall.model.User;
import com.caroline.springbootmall.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User register(UserRequestDto dto) {

        //check registering email
        User existUser= userDao.getUserByEmail(dto.getEmail());
        if(existUser != null){
             log.warn("email:{} has been registered.", dto.getEmail());
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //create user
        return  userDao.createUser(dto);

    }
}
