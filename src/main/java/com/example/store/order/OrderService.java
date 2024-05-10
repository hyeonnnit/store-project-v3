package com.example.store.order;

import com.example.store.cart.Cart;
import com.example.store.cart.CartRepository;
import com.example.store.cart.CartRequest;
import com.example.store.cart.CartResponse;
import com.example.store.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public List<OrderResponse.ListDTO> getOrderList() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(OrderResponse.ListDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public OrderResponse.DetailDTO saveOrder(OrderRequest.SaveDTO saveDTO, User user) {
        Cart cart = cartRepository.findByCartUserId(user.getId());
        Order order = orderRepository.save(saveDTO.toEntity(user, cart));
        return new OrderResponse.DetailDTO(order);
    }

}
