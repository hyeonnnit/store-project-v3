package com.example.store.order;

import com.example.store.cart.Cart;
import com.example.store.user.User;
import jakarta.persistence.Column;
import lombok.Data;

public class OrderResponse {
    @Data
    public static class ListDTO{
        private Integer id;
        private Integer cartId;
        private String name;
        private int price ;
        private int qty;
        private String pic;
        private Integer orderQty;
        private Integer totalPrice;
        private Integer sum;
        private String status;

        public ListDTO(Order order){
            this.id = order.getId();
            this.cartId=order.getCart().getId();
            this.name = order.getCart().getProduct().getName();
            this.price = order.getCart().getProduct().getPrice();
            this.qty = order.getCart().getProduct().getQty();
            this.pic = order.getCart().getProduct().getPic();
            this.orderQty = order.getCart().getOrderQty();
            this.totalPrice = order.getTotalPrice();
            this.sum = order.getSum();
            this.status = order.getStatus();
        }
    }
}