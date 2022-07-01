package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dto.UserLoginRequestDto;
import com.caroline.springbootmall.dto.UserRegisterRequestDto;
import com.caroline.springbootmall.model.User;
import com.caroline.springbootmall.service.UserService;
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
    public User register(UserRegisterRequestDto dto) {

        //check registering email
        User existUser= userDao.getUserByEmail(dto.getEmail());
        if(existUser != null){
             log.warn("email:{} has been registered.", dto.getEmail());
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //create user
        return  userDao.createUser(dto);

    }

    @Override
    public User login(UserLoginRequestDto loginDto) {
        User user= userDao.getUserByEmail(loginDto.getEmail());

        if(user == null){
            log.warn("email:{} hasn't been registered.", loginDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(loginDto.getPassword().equals(user.getPassword())){
            return user;
        }else {
            log.warn("email:{} password is not valid.", loginDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
