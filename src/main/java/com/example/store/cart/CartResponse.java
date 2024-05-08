package com.example.store.cart;

import com.example.store.product.Product;
import com.example.store.user.User;
import lombok.Data;

public class CartResponse {
    @Data
    public static class DetailDTO{
        private int id;
        private User user;
        private Product product;
        private String name;
        private int price ;
        private int qty;
        private String pic;
        private Integer orderQty;



        public DetailDTO(Cart cart){
            this.id = cart.getId();
            this.user=cart.getUser();
            this.product=cart.getProduct();
            this.orderQty = cart.getOrderQty();
            this.name = cart.getProduct().getName();
            this.price = cart.getProduct().getPrice();
            this.qty = cart.getProduct().getQty();
            this.pic = cart.getProduct().getPic();
        }
    }

    @Data
    public static class ListDTO{
        private int id;
        private User user;
        private Integer productId;
        private String name;
        private int price ;
        private int qty;
        private String pic;
        private Integer orderQty;

        public ListDTO(Cart cart){
            this.id = cart.getId();
            this.user=cart.getUser();
            this.productId=cart.getProduct().getId();
            this.orderQty = cart.getOrderQty();
            this.name = cart.getProduct().getName();
            this.price = cart.getProduct().getPrice();
            this.qty = cart.getProduct().getQty();
            this.pic = cart.getProduct().getPic();
        }
    }
}