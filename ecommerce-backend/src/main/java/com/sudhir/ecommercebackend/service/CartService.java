package com.sudhir.ecommercebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.sudhir.ecommercebackend.entity.OrderEntity;
import com.sudhir.ecommercebackend.entity.OrderItem;
import com.sudhir.ecommercebackend.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.sudhir.ecommercebackend.dto.CartResponseDTO;
import com.sudhir.ecommercebackend.dto.AddToCartDTO;
import com.sudhir.ecommercebackend.entity.CartItem;
import com.sudhir.ecommercebackend.entity.Product;
import com.sudhir.ecommercebackend.entity.User;
import com.sudhir.ecommercebackend.repository.CartItemRepository;
import com.sudhir.ecommercebackend.repository.OrderItemRepository;
import com.sudhir.ecommercebackend.repository.ProductRepository;
import com.sudhir.ecommercebackend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public String addToCart(AddToCartDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElse(null);

        Product product = productRepository.findById(dto.getProductId())
                .orElse(null);

        if(user == null || product == null) {

            return "User or Product not found";
        }

        CartItem cartItem = new CartItem();

        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());

        cartItemRepository.save(cartItem);

        return "Product added to cart";
    }
    public List<CartResponseDTO> getCartByUserId(
            Long userId) {

        List<CartItem> cartItems =
                cartItemRepository.findByUserId(userId);

        List<CartResponseDTO> response =
                new ArrayList<>();

        for(CartItem item : cartItems) {

        	response.add(

        	        new CartResponseDTO(
        	                item.getId(),
        	                item.getProduct().getName(),
        	                item.getProduct().getPrice(),
        	                item.getQuantity()
        	        )
        	);
        }

        return response;
    }
    public String removeCartItem(Long cartItemId) {

        cartItemRepository.deleteById(cartItemId);

        return "Cart item removed";
    }
    @Transactional
    public String placeOrder(Long userId) {

        User user = userRepository.findById(userId)
                .orElse(null);

        if(user == null) {

            return "User not found";
        }

        List<CartItem> cartItems =
                cartItemRepository.findByUserId(userId);

        if(cartItems.isEmpty()) {

            return "Cart is empty";
        }

        double total = 0;

        for(CartItem item : cartItems) {

            total += item.getProduct().getPrice()
                    * item.getQuantity();
        }

        OrderEntity order = new OrderEntity();

        order.setUser(user);
        order.setTotalAmount(total);

        orderRepository.save(order);

        for(CartItem item : cartItems) {
        	
        	Product product = item.getProduct();
        	if(item.getQuantity() > product.getStock()) {

                return product.getName()

                        + " does not have enough stock";

            }

        	product.setStock(
        	        product.getStock()
        	        - item.getQuantity());

        	productRepository.save(product);

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);

            orderItem.setProduct(item.getProduct());

            orderItem.setQuantity(item.getQuantity());

            orderItem.setPrice(
                    item.getProduct().getPrice());

            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteByUserId(userId);

        return "Order placed successfully";
    }
    @Autowired
    private OrderItemRepository orderItemRepository;
}