package com.example.store.order;

import com.example.store.product.Product;
import com.example.store.product.ProductRepository;
import com.example.store.user.User;
import com.example.store.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    @Transactional
    public OrderResponse.DetailDTO orderSaveProduct(Integer productId, User user, OrderRequest.SaveDTO reqDTO){
        Product product = productRepository.findById(productId);
        if (product.getQty() < reqDTO.getOrderNum()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        product.setQty(product.getQty() - reqDTO.getOrderNum());
        productRepository.updateQty(product);
        Order order = orderRepository.save(reqDTO.toEntity(user, product));
        order.setPriceSum(product.getPrice()*reqDTO.getOrderNum());
        return new OrderResponse.DetailDTO(order);
    }

//    @Transactional
//    public Order editProduct( int id, OrderRequest.UpdateDTO reqDTO){
//        Order order = orderRepository.updateById(id, reqDTO);
//        return order;
//    }
//
//    public OrderResponse.DetailDTO getOrderUpdate(int productId, User user){
//        Product product = productRepository.findById(productId);
//        Order order = orderRepository.findByOrderId(user, product);
//        return new OrderResponse.DetailDTO(order);
//    }

    public OrderResponse.DetailDTO getOrderDetail(int id){
        Order order = orderRepository.findByProductId(id);
        return new OrderResponse.DetailDTO(order);
    }

    public List<OrderResponse.ListDTO> getOrderList(int userId){
        List<Order> orderList = orderRepository.findProductByUserId(userId);
        return orderList.stream().map(OrderResponse.ListDTO::new).collect(Collectors.toList());
    }

    public OrderResponse.DetailDTO getOrderProduct(int productId, int userId){
        Order order = orderRepository.findByOrderId(productId, userId);
        return new OrderResponse.DetailDTO(order);
    }

    @Transactional
    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }
}
