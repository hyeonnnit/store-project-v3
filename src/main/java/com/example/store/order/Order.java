package com.example.store.order;

import com.example.store.cart.Cart;
import com.example.store.product.Product;
import com.example.store.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "order_tb")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @Column
    private Integer totalPrice;   // 구매 가격

    @Column
    private Integer sum;

    @Column
    private String status;      // 주문 상태 -> true: 주문 완료, false: 주문 취소

//    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Order(Integer id, User user, Cart cart, Integer totalPrice, Integer sum, String status, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.cart = cart;
        this.sum = sum;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }
}
