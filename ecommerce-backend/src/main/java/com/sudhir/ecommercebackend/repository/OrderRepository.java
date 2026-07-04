package com.sudhir.ecommercebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudhir.ecommercebackend.entity.OrderEntity;

public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {
	List<OrderEntity> findByUserId(Long userId);

}