package com.example.store.order;

import com.example.store.product.Product;
import com.example.store.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    private Product product;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Order(Integer id, User user, Product product, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.createdAt = createdAt;
    }
}
