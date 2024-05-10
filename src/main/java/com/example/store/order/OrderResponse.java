package com.example.store.order;


import com.example.store.product.Product;
import lombok.Data;

public class OrderResponse {

    @Data
    public static class DetailDTO{
        private Integer id;
        private Integer cartId;
        private Product product;
        private String name;
        private int price ;
        private int qty;
        private String pic;
        private Integer orderQty;
        private boolean checkedBox;
        private Integer totalPrice;
        private Integer sum;
        private String status;

        public DetailDTO(Order order){
            this.id = order.getId();
            this.cartId=order.getCart().getId();
            this.product = order.getCart().getProduct();
            this.name = order.getCart().getProduct().getName();
            this.price = order.getCart().getProduct().getPrice();
            this.qty = order.getCart().getProduct().getQty();
            this.pic = order.getCart().getProduct().getPic();
            this.checkedBox = order.getCart().isCheckedBox();
            this.orderQty = order.getCart().getOrderQty();
            this.totalPrice = order.getTotalPrice();
            this.sum = order.getSum();
            this.status = order.getStatus();
        }
    }

    @Data
    public static class ListDTO{
        private Integer id;
        private Integer cartId;
        private Product product;
        private String name;
        private int price ;
        private int qty;
        private String pic;
        private Integer orderQty;
        private boolean checkedBox;
        private Integer totalPrice;
        private Integer sum;
        private String status;

        public ListDTO(Order order){
            this.id = order.getId();
            this.cartId=order.getCart().getId();
            this.product = order.getCart().getProduct();
            this.name = order.getCart().getProduct().getName();
            this.price = order.getCart().getProduct().getPrice();
            this.qty = order.getCart().getProduct().getQty();
            this.pic = order.getCart().getProduct().getPic();
            this.checkedBox = order.getCart().isCheckedBox();
            this.orderQty = order.getCart().getOrderQty();
            this.totalPrice = order.getTotalPrice();
            this.sum = order.getSum();
            this.status = order.getStatus();
        }
    }
}