package com.sudhir.ecommercebackend.controller;

import java.util.List;
import jakarta.validation.Valid;
import com.sudhir.ecommercebackend.dto.LoginRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sudhir.ecommercebackend.dto.UserResponseDTO;
import com.sudhir.ecommercebackend.entity.User;
import com.sudhir.ecommercebackend.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody User user) {

        return userService.registerUser(user);
    }
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {

        return userService.getAllUsers();
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDTO loginDTO) {

        return userService.loginUser(loginDTO);
    }
}