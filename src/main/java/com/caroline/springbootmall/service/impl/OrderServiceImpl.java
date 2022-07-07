package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.OrderDao;
import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.User;
import com.caroline.springbootmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Order createOrder(Integer userId, OrderCreateRequestDto createOrderRequestDto) {
        return orderDao.createOrder(userId, createOrderRequestDto);
    }

    @Override
    public List<Order> getUserOrders(Integer userId) {
        User user = userDao.findUserById(userId);
        if(user == null){
            log.warn("userId:{} not found.", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return orderDao.getUserOrders(userId);
    }
}
