package com.caroline.springbootmall.service.impl;

import com.caroline.springbootmall.dao.OrderDao;
import com.caroline.springbootmall.dao.ProductDao;
import com.caroline.springbootmall.dao.UserDao;
import com.caroline.springbootmall.dto.BuyItem;
import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.model.OrderItem;
import com.caroline.springbootmall.model.Product;
import com.caroline.springbootmall.model.User;
import com.caroline.springbootmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Integer createOrder(Integer userId, OrderCreateRequestDto createOrderRequestDto) {
        //check user
        checkIfUserExist(userId);

       //conver BuyItem to orderItem
        List<OrderItem> orderItems = new ArrayList<>();
        Integer totalAmount = 0;
        for (BuyItem buyItem : createOrderRequestDto.getBuyItemList()) {

            //caculate amount and totalAmount
            Product product = productDao.getProductById(buyItem.getProductId());
            Integer amount = product.getPrice() * buyItem.getQuantity();
            totalAmount += amount;

            //check stock
            if(product.getStock() < buyItem.getQuantity()){
                log.warn("product:{}/stock:{} is not enough or out of stock.", product.getProductName(), product.getStock());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //set orderItems
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItem.setProductId(product.getProductId());
            orderItems.add(orderItem);
        }

        //create order
        Integer newOrderId = orderDao.createOrder(userId, createOrderRequestDto, totalAmount);

        //create orderItem
        orderDao.createOrderItems(newOrderId,orderItems);

        //update stock
        productDao.updateProductInventory(orderItems);

        return newOrderId;
    }

    @Override
    public List<Order> getUserOrders(Integer userId) {
        checkIfUserExist(userId);

        //setOrderItems
        List<Order> orders = orderDao.getUserOrders(userId);
        orders.forEach(order->order.setOrderItems(orderDao.getOrderItemsByOrderId(order.getOrderId())));
        return orders;
    }

    @Override
    public Order getOrderById(Integer orderId) {

        Order existOrder = orderDao.getOrderById(orderId);
        if(existOrder == null){
            log.warn("orderId:{} is not fount.", orderId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<OrderItem> orderItems = orderDao.getOrderItemsByOrderId(orderId);
        existOrder.setOrderItems(orderItems);
        return existOrder;
    }

    private void checkIfUserExist(Integer userId){
        User user = userDao.findUserById(userId);
        if(user == null){
            log.warn("userId:{} not found.", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
