package com.example.store.order;

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
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


    @Transactional
    public Order orderSaveProduct(Integer productId, User user, OrderRequest.SaveDTO reqDTO){
        Product product = productRepository.findById(productId);
        return orderRepository.save(reqDTO.toEntity(user, product));
    }

    @Transactional
    public Order editProduct( int id, OrderRequest.UpdateDTO reqDTO){
        return orderRepository.updateById(id, reqDTO);
    }

    public OrderResponse.DetailDTO getOrderDetail(int productId, User sessionUser){
        Product product = productRepository.findById(productId);
        Order order = orderRepository.findByProductIdAndUserId(product, sessionUser);
        return new OrderResponse.DetailDTO(order);
    }

    public List<OrderResponse.ListDTO> getOrderList(int userId){
        List<Order> orderList = orderRepository.findProductByUserId(userId);
        return orderList.stream().map(OrderResponse.ListDTO::new).collect(Collectors.toList());
    }

    public Product getOrderProduct(int productId){
        Product product = productRepository.findById(productId);
        return product;
    }

    @Transactional
    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }
}
