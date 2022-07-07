package com.caroline.springbootmall.dao.impl;

import com.caroline.springbootmall.dao.OrderDao;
import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dao.repository.OrderItemRepository;
import com.caroline.springbootmall.dao.repository.OrderRepository;
import com.caroline.springbootmall.dao.repository.ProductRepository;
import com.caroline.springbootmall.dao.repository.UserRepository;
import com.caroline.springbootmall.dto.BuyItem;
import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.OrderItem;
import com.caroline.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    public Order createOrder(Integer userId, OrderCreateRequestDto dto) {

        //set orderItems
        List<OrderItem> orderItems = new ArrayList<>();
        dto.getItems().forEach(item-> {
        orderItems.add(OrderItem.getOrderItem(productDao.getProductById(item.getProductId()), item.getQuantity()));
        });

        //caculate totalAmount
        Integer totalAmount = 0;
        for (OrderItem item : orderItems) {
            totalAmount += item.getAmount();
        }

        //save order
        Order order = new Order();
        order.setUserId(userId);
        order.setCreatedDate(LocalDateTime.now());
        order.setLastModifiedDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        Order newOrder = orderRepo.save(order);

        //save orderItems
        for (OrderItem item : orderItems) {
           item.setOrderId(newOrder.getOrderId());
        }
        orderItemRepo.saveAll(orderItems);

        return  newOrder;
    }
}
