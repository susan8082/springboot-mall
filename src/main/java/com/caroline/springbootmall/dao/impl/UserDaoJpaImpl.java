package com.caroline.springbootmall.dao.impl;

import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dao.repository.UserRepository;
import com.caroline.springbootmall.dto.UserRegisterRequestDto;
import com.caroline.springbootmall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoJpaImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return  userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        System.out.println("getUserByEmail:"+email);
        List<User> users = userRepository.findByEmail(email);
        System.out.println("users.size:"+users.size());
        if (users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    public User findUserById(Integer userId) {
        List<User> users = userRepository.findByUserId(userId);
        if (users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }
}
