package com.example.store.cart;

import com.example.store.order.Order;
import com.example.store.order.OrderRequest;
import com.example.store.order.OrderResponse;
import com.example.store.product.Product;
import com.example.store.product.ProductRepository;
import com.example.store.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CartResponse.DetailDTO cartSaveProduct(Integer productId, User user, CartRequest.SaveDTO reqDTO){
        Product product = productRepository.findById(productId);
        if (product.getQty() < reqDTO.getOrderQty()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        product.setQty(product.getQty() - reqDTO.getOrderQty());
        productRepository.updateQty(product);
        Cart cart = cartRepository.save(reqDTO.toEntity(user, product));
        cart.setTotalPrice(product.getPrice()*reqDTO.getOrderQty());
        return new CartResponse.DetailDTO(cart);
    }
    public CartResponse.DetailDTO getCartDetail(int id){
        Cart cart = cartRepository.findByProductId(id);
        return new CartResponse.DetailDTO(cart);
    }

    public List<CartResponse.ListDTO> getCartList(int userId){
        List<Cart> orderList = cartRepository.findProductByUserId(userId);
        return orderList.stream().map(CartResponse.ListDTO::new).collect(Collectors.toList());
    }
}
