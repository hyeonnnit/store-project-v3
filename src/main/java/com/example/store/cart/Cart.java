package com.example.store.cart;

import com.example.store.product.Product;
import com.example.store.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "cart_tb")
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 주문을 여러번 할수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 여러번 상품을 주문할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private Integer orderQty;   // 주문 수량

    @Column
    private Integer totalPrice;   // 구매 총 가격

//    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Cart(Integer id, User user, Product product, Integer orderQty, Integer totalPrice, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.orderQty = orderQty;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
}
