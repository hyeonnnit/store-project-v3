package com.example.store.order;

import com.example.store.cart.Cart;
import com.example.store.product.Product;
import com.example.store.user.User;
import lombok.Data;

public class OrderRequest {

    @Data
    public static class SaveDTO {
        private User user;
        private Cart cart;
        private Integer totalPrice;
        private Integer sum;
        private String status;


        public Order toEntity(User user, Cart cart) {
            return Order.builder()
                    .user(user)
                    .cart(cart)
                    .totalPrice(totalPrice)
                    .sum(sum)
                    .status(status)
                    .build();
        }
    }
}
