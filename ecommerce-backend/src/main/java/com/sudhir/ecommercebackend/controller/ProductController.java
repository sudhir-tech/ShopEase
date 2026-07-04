package com.sudhir.ecommercebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import com.sudhir.ecommercebackend.dto.ProductDTO;
import com.sudhir.ecommercebackend.entity.Product;
import com.sudhir.ecommercebackend.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {

        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productService.getProductById(id);	
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }
    
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        return productService.deleteProduct(id);
    }
    @GetMapping("/dto")
    public List<ProductDTO> getProductDTOs() {

        return productService
                .getAllProductDTOs();
    }
    @GetMapping("/pagination")
    public Page<Product> getProductsWithPagination(

            @RequestParam int page,

            @RequestParam int size) {

        return productService
                .getProductsWithPagination(
                        page,
                        size);
    }
    @GetMapping("/sort")
    public List<Product> sortProducts(

            @RequestParam String field) {

        return productService
                .sortProducts(field);
    }
    @GetMapping("/search")
    public List<Product> searchByName(

            @RequestParam String name) {

        return productService
                .searchProducts(name);
    }
    @GetMapping("/pagination-sort")
    public Page<Product>
    getProductsWithPaginationAndSorting(

            @RequestParam int page,

            @RequestParam int size,

            @RequestParam String field) {

        return productService
                .getProductsWithPaginationAndSorting(
                        page,
                        size,
                        field);
    }
}