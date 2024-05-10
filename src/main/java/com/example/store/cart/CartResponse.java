package com.example.store.cart;

import com.example.store.user.User;
import lombok.Data;

public class CartResponse {


    @Data
    public static class CartDTO{
        private int id;
        private User user;
        private Integer productId;
        private String name;
        private int price ;
        private int qty;
        private boolean checkedBox;
        private String pic;
        private Integer orderQty;

        public CartDTO(Cart cart){
            this.id = cart.getId();
            this.user=cart.getUser();
            this.productId=cart.getProduct().getId();
            this.orderQty = cart.getOrderQty();
            this.checkedBox = cart.isCheckedBox();
            this.name = cart.getProduct().getName();
            this.price = cart.getProduct().getPrice();
            this.qty = cart.getProduct().getQty();
            this.pic = cart.getProduct().getPic();
        }
    }
}