package com.example.store.order;

import com.example.store.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Order editProduct(int id, OrderRequest.UpdateDTO reqDTO){
        return orderRepository.updateById(id, reqDTO);

    }
    public OrderResponse.OrderDTO getOrderProduct(int productId, int userId){
        Order order = orderRepository.findByProductIdAndUserId(productId, userId);
        return new OrderResponse.OrderDTO(order);
    }

    public List<OrderResponse.OrderDTO> getOrderList(int userId){
        List<Order> orderList = orderRepository.findProductByUserId(userId);
        return orderList.stream().map(OrderResponse.OrderDTO::new).collect(Collectors.toList());
    }

    public Order getOrder(int productId, int userId){
        return orderRepository.findByProductIdAndUserId(productId, userId);
    }

}
