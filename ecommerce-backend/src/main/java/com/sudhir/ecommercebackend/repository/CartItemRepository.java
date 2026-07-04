package com.sudhir.ecommercebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudhir.ecommercebackend.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUserId(Long userId);
	void deleteByUserId(Long userId);

}
