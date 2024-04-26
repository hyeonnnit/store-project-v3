package com.example.store.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    //상품 상세 보기
    public ProductResponse.DetailDTO getProductDetail(int id){
        return productRepository.findById(id);
    }
    //상품 목록보기
    public List<ProductResponse.ListDTO> getProductList(){
        return productRepository.findAll();
    }

}
