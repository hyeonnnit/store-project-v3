package com.example.store.cart;


import com.example.store.order.Order;
import com.example.store.product.Product;
import com.example.store.user.User;
import jakarta.persistence.Column;
import lombok.Data;

public class CartRequest {
    @Data
    public static class SaveDTO {
        private Integer orderQty;

        public Cart toEntity(User user, Product product) {
            return Cart.builder()
                    .user(user)
                    .product(product)
                    .orderQty(orderQty)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private Integer cartId;
        private Integer orderQty;
    }
}