package com.example.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;

    public ProductResponse.DetailDTO findById(int id) {
        Product product = em.find(Product.class, id);
        return new ProductResponse.DetailDTO(product);
    }

    public List<ProductResponse.ListDTO> findAll() {
        Query query =
                em.createQuery("select p from Product p order by p.id desc", Product.class);
        return query.getResultList();
    }
}
