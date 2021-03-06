package com.caroline.springbootmall.dao.repository;

import com.caroline.springbootmall.model.Product;
import com.caroline.springbootmall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String email);
    List<User> findByUserId(Integer userId);
}
