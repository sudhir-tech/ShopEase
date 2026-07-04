package com.sudhir.ecommercebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sudhir.ecommercebackend.entity.OrderEntity;
import com.sudhir.ecommercebackend.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{userId}")
    public List<OrderEntity> getOrders(
            @PathVariable Long userId) {

        return orderService.getOrdersByUser(userId);
    }
}