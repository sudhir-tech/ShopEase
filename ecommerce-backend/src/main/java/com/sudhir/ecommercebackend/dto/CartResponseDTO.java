package com.sudhir.ecommercebackend.dto;

public class CartResponseDTO {

    private Long cartItemId;
    private String productName;
    private Double price;
    private Integer quantity;

    public CartResponseDTO() {
    }

    public CartResponseDTO(Long cartItemId,
                           String productName,
                           Double price,
                           Integer quantity) {
        this.cartItemId = cartItemId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}