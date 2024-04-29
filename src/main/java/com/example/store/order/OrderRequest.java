package com.example.store.order;

import com.example.store.product.Product;
import com.example.store.user.User;
import lombok.Data;

public class OrderRequest {

    @Data
    public static class UpdateDTO{
        private Integer orderNum;
    }

    @Data
    public static class SaveDTO{
        private Integer orderNum;
        public Order toEntity(User user, Product product){
            return Order.builder()
                    .user(user)
                    .product(product)
                    .orderNum(orderNum)
                    .build();
        }
    }
}
