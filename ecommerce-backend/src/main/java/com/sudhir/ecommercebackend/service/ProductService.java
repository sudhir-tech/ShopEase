package com.sudhir.ecommercebackend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.sudhir.ecommercebackend.dto.ProductDTO;
import com.sudhir.ecommercebackend.entity.Product;
import com.sudhir.ecommercebackend.repository.ProductRepository;

@Service
public class ProductService {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {

        logger.info("Fetching product {} from MySQL", id);

        return productRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "products", key = "#id")
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct =
                productRepository.findById(id).orElse(null);

        if (existingProduct != null) {

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());

            return productRepository.save(existingProduct);
        }

        return null;
    }

    @CacheEvict(value = "products", key = "#id")
    public String deleteProduct(Long id) {

        productRepository.deleteById(id);

        return "Product deleted successfully";
    }

    public List<ProductDTO> getAllProductDTOs() {

        List<Product> products =
                productRepository.findAll();

        List<ProductDTO> dtoList =
                new ArrayList<>();

        for (Product product : products) {

            ProductDTO dto =
                    new ProductDTO(
                            product.getName(),
                            product.getPrice());

            dtoList.add(dto);
        }

        return dtoList;
    }

    public Page<Product> getProductsWithPagination(int page, int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }

    public List<Product> searchProducts(String name) {

        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/search")
    public List<Product> sortProducts(String field) {

        return productRepository.findAll(
                Sort.by(field));
    }

    public Page<Product> getProductsWithPaginationAndSorting(
            int page,
            int size,
            String field) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(field));

        return productRepository.findAll(pageable);
    }
}