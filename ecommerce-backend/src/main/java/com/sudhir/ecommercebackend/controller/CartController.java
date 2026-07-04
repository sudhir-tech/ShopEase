package com.sudhir.ecommercebackend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import com.sudhir.ecommercebackend.dto.AddToCartDTO;
import com.sudhir.ecommercebackend.dto.CartResponseDTO;
import com.sudhir.ecommercebackend.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(
            @RequestBody AddToCartDTO dto) {

        return cartService.addToCart(dto);
    }
    @GetMapping("/user/{userId}")
    public List<CartResponseDTO> getCartByUserId(
            @PathVariable Long userId) {

        return cartService.getCartByUserId(userId);
    }
    @DeleteMapping("/remove/{cartItemId}")
    public String removeCartItem(
            @PathVariable Long cartItemId) {

        return cartService.removeCartItem(cartItemId);
    }
    @PostMapping("/place-order/{userId}")
    public String placeOrder(
            @PathVariable Long userId) {

        return cartService.placeOrder(userId);
    }
}