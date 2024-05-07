package com.example.store.order;

import com.example.store.cart.Cart;
import com.example.store.cart.CartRepository;
import com.example.store.cart.CartResponse;
import com.example.store.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Transactional
    public List<OrderResponse.ListDTO> getOrderList(OrderRequest.SaveDTO reqDTO, User sessionUser, Integer id){
        Cart cart = cartRepository.findById(sessionUser.getId());
        Order order = orderRepository.save(reqDTO.toEntity(sessionUser,cart));
        List<Order> orderList = orderRepository.findByUserId(id);
        orderList.add(order);
        return orderList.stream().map(OrderResponse.ListDTO::new).collect(Collectors.toList());
    }
}
