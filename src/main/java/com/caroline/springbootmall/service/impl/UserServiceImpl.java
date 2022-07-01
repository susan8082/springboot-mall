package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dto.UserLoginRequestDto;
import com.caroline.springbootmall.dto.UserRegisterRequestDto;
import com.caroline.springbootmall.model.User;
import com.caroline.springbootmall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.cli.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
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
        //covert to Hash values with MD5
        String hashedPassword = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
        dto.setPassword(hashedPassword);
        log.info("hashedPassword:{}", hashedPassword);

        //create user
        return  userDao.createUser(UserRegisterRequestDto.convertToUser(dto));

    }

    @Override
    public User login(UserLoginRequestDto loginDto) {
        User user= userDao.getUserByEmail(loginDto.getEmail());

        //check user if exist
        if(user == null){
            log.warn("email:{} hasn't been registered.", loginDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //covert to Hash values with MD5
        String hashedPassword = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());

        //check password if valid
        if(hashedPassword.equals(user.getPassword())){
            return user;
        }else {
            log.warn("email:{} password is not valid.", loginDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
