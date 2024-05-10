package com.example.store.cart;

import com.example.store.order.Order;
import com.example.store.product.Product;
import com.example.store.product.ProductRepository;
import com.example.store.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

//    @Transactional
//    public List<Cart> updateCart(List<CartRequest.UpdateDTO> updateDTOList, Integer userId){
//        List<Cart> cartList = cartRepository.findProductByUserId(userId);
//        for (CartRequest.UpdateDTO reqDTO : updateDTOList){
//            Cart cart = cartRepository.findById(reqDTO.getCartId());
//            cartRepository.updateById(reqDTO);
//            cartRepository.deleteById(cart.getId());
//            cartList.add(cart);
//        }
//        return cartList;
//    }

    @Transactional
    public List<Cart> updateCart(List<CartRequest.UpdateDTO> updateDTOList) {
        List<Cart> cartList = new ArrayList<>();
        for (CartRequest.UpdateDTO reqDTO : updateDTOList) {
            cartRepository.updateById(reqDTO);
            cartRepository.deleteById(reqDTO.getCartId());
        }
        return cartList;
    }

    @Transactional
    public CartResponse.CartDTO saveCart(Integer productId, User user, CartRequest.SaveDTO reqDTO) {
        Product product = productRepository.findById(productId);
        Cart cart = cartRepository.save(reqDTO.toEntity(user, product));
        cart.setOrderQty(reqDTO.getOrderQty());
        if (product.getQty() < reqDTO.getOrderQty()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        product.setQty(product.getQty() - reqDTO.getOrderQty());
        productRepository.updateQty(product);
        cartRepository.save(cart);
        return new CartResponse.CartDTO(cart);
    }

    public List<CartResponse.CartDTO> getCartList(int userId) {
        List<Cart> cartList = cartRepository.findProductByUserId(userId);
        return cartList.stream().map(CartResponse.CartDTO::new).collect(Collectors.toList());
    }
}
