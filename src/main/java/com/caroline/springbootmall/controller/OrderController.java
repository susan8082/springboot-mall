package com.caroline.springbootmall.controller;

import com.caroline.springbootmall.dto.OrderCreateRequestDto;
import com.caroline.springbootmall.model.Order;
import com.caroline.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId, @RequestBody OrderCreateRequestDto orderRequestDto){
        Order order = orderService.createOrder(userId, orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/{userId}/orders/")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Integer userId){
        List<Order> orders = orderService.getUserOrders();
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }
}
