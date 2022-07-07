package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.OrderDao;
import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order createOrder(Integer userId, OrderCreateRequestDto createOrderRequestDto) {
        return orderDao.createOrder(userId, createOrderRequestDto);
    }
}
