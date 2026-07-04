package com.sudhir.ecommercebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudhir.ecommercebackend.entity.OrderItem;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

}