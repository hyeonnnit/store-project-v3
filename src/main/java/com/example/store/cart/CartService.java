package com.example.store.cart;

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
    public void updateCart(List<CartRequest.UpdateDTO> updateDTOList){
        for (CartRequest.UpdateDTO reqDTO : updateDTOList){
            cartRepository.deleteById(reqDTO.getCartId());
        }
    }
    @Transactional
    public Cart saveCart(Integer productId, User user, CartRequest.SaveDTO reqDTO){
        Product product = productRepository.findById(productId);
        Cart cart = cartRepository.save(reqDTO.toEntity(user, product));
        cart.setOrderQty(reqDTO.getOrderQty());
        if (product.getQty() < reqDTO.getOrderQty()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        product.setQty(product.getQty() - reqDTO.getOrderQty());
        productRepository.updateQty(product);
        cartRepository.save(cart);
        return cart;
    }
    public Cart getCart(int id){
        Cart cart = cartRepository.findById(id);
        return cart;
    }

    public List<CartResponse.ListDTO> getCartList(int userId){
        List<Cart> cartList = cartRepository.findProductByUserId(userId);
        return cartList.stream().map(CartResponse.ListDTO::new).collect(Collectors.toList());
    }
}
