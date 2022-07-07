package com.caroline.springbootmall.dao.impl;

import com.caroline.springbootmall.dao.OrderDao;
import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dao.repository.OrderItemRepository;
import com.caroline.springbootmall.dao.repository.OrderRepository;
import com.caroline.springbootmall.dao.repository.UserRepository;
import com.caroline.springbootmall.dto.BuyItem;
import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAOJpalmpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrderItemRepository orderItemRepo;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Integer createOrder(Integer userId, OrderCreateRequestDto dto, Integer totalAmount) {

        Order order = new Order();
        order.setUserId(userId);
        order.setCreatedDate(LocalDateTime.now());
        order.setLastModifiedDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        Order newOrder = orderRepo.save(order);
        return  newOrder.getOrderId();
    }

    @Override
    public void createOrderItems(Integer orderId ,List<OrderItem> orderItems) {
        orderItems.forEach(item->item.setOrderId(orderId));
        orderItemRepo.saveAll(orderItems);
    }

    @Override
    public List<Order> getUserOrders(Integer userId) {
        return orderRepo.findByUserId(userId);
    }
}
