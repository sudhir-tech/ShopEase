package com.sudhir.ecommercebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhir.ecommercebackend.entity.OrderEntity;
import com.sudhir.ecommercebackend.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getOrdersByUser(
            Long userId) {

        return orderRepository.findByUserId(userId);
    }
}