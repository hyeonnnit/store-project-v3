package com.example.store.cart;

import com.example.store.order.Order;
import com.example.store.order.OrderRequest;
import com.example.store.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final EntityManager em;

    public void updateById(Integer id, CartRequest.UpdateDTO reqDTO){
        Cart cart = findById(id);
        cart.setId(reqDTO.getCartId());
        cart.setOrderQty(reqDTO.getOrderQty());
    }
    public void deleteById(Integer id){
        Query query = em.createQuery("delete from Cart c where c.id= :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
    public Cart save(Cart cart){
        em.persist(cart);
        return cart;
    }

    public Cart findById(int id) {
        return em.find(Cart.class, id);
    }
    public Cart findByProductId(int id) {
        Query query = em.createQuery("select c from Cart c JOIN FETCH c.product p WHERE p.id =:product_id");
        query.setParameter("product_id", id);
        return (Cart) query.getSingleResult();
    }

    public List<Cart> findProductByUserId(int userId) {
        Query query =
                em.createQuery("select c from Cart c JOIN FETCH c.product p JOIN FETCH c.user u WHERE u.id = :user_id", Cart.class);
        query.setParameter("user_id", userId);
        return query.getResultList();
    }
}
