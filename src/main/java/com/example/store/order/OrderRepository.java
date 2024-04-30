package com.example.store.order;

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

    public void deleteById(int id){
        Query query = em.createQuery("DELETE FROM Order o where o.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Order save(Order order){
        em.persist(order);
        return order;
    }
    public Order updateById(int id, OrderRequest.UpdateDTO reqDTO){
        Order order = findById(id);
        order.setOrderNum(reqDTO.getOrderNum());
        return order;
    }

    public Order findById(int id) {
        Order order = em.find(Order.class, id);
        return order;
    }

    public Order findByProductIdAndUserId(Product product, User user) {
        Query query = em.createQuery("select o from Order o JOIN FETCH o.product p JOIN FETCH o.user u WHERE p.id =:product_id and u.id =:user_id");
        query.setParameter("product_id", product.getId());
        query.setParameter("user_id", user.getId());
        return (Order) query.getSingleResult();
    }

    public List<Order> findProductByUserId(int userId) {
        Query query =
                em.createQuery("select o from Order o JOIN FETCH o.product p JOIN FETCH o.user u WHERE u.id = :user_id", Order.class);
        query.setParameter("user_id", userId);
        return query.getResultList();
    }
}
