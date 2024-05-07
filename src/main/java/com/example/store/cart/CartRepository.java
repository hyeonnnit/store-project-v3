package com.example.store.cart;

import com.example.store.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final EntityManager em;


    public Cart save(Cart cart){
        em.persist(cart);
        return cart;
    }


    public Cart findByProductId(int id) {
        Query query = em.createQuery("select c from Cart c JOIN FETCH c.product p WHERE p.id =:id");
        query.setParameter("id", id);
        return (Cart) query.getSingleResult();
    }

    public List<Cart> findProductByUserId(int userId) {
        Query query =
                em.createQuery("select c from Cart c JOIN FETCH c.product p JOIN FETCH c.user u WHERE u.id = :user_id", Order.class);
        query.setParameter("user_id", userId);
        return query.getResultList();
    }
}
