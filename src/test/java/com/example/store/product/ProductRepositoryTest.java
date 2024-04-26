package com.example.store.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(ProductRepository.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll_test() {
        // given

        // when
        List<ProductResponse.ListDTO> productList = productRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + productList.size());
        System.out.println("findAll_test/name : " + productList.get(2).getName());

        // org.assertj.core.api
        Assertions.assertThat(productList.size()).isEqualTo(5);
        Assertions.assertThat(productList.get(2).getName()).isEqualTo("수박");
    }
}
