package com.example.store.order;

import com.example.store.product.Product;
import lombok.Data;

public class OrderResponse {

    @Data
    public static class OrderDTO{
        private int id;
        private String name;
        private int price ;
        private int qty;
        private String pic;
        private Integer orderNum;

        public OrderDTO(Order order){
            this.id = order.getProduct().getId();
            this.orderNum = order.getOrderNum();
            this.name = order.getProduct().getName();
            this.price = order.getProduct().getPrice();
            this.qty = order.getProduct().getQty();
            this.pic = order.getProduct().getPic();
        }
    }
}
