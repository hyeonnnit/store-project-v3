package com.example.store.order;

import com.example.store.cart.Cart;
import com.example.store.product.Product;
import com.example.store.product.ProductRepository;
import com.example.store.product.ProductResponse;
import com.example.store.user.User;
import com.example.store.user.UserRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public List<Order> findByUserId(int id) {
        Query query =
                em.createQuery("select o from Order o JOIN FETCH o.cart c join fetch c.product p join fetch c.user u WHERE o.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
    public List<Order> findAll() {
        Query query =
                em.createQuery("select o from Order o order by o.id", Order.class);
        return query.getResultList();
    }
    public Order save(Order order){
        em.persist(order);
        return order;
    }
}
