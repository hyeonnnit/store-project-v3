package com.example.store.order;

import com.example.store.cart.CartRequest;
import com.example.store.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;
    private final HttpSession session;

    // 주문 목록
    @GetMapping("/order-list")
    public String saveOrderList(HttpServletRequest request) {
        List<OrderResponse.ListDTO> orderList = orderService.getOrderList();
        System.out.println(orderList);
        request.setAttribute("orderList", orderList);
        return "order/list";
    }

    @PostMapping("/order/{id}/save")
    public String saveOrder(@PathVariable Integer id, OrderRequest.SaveDTO saveDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        orderService.saveOrder(saveDTO,id,sessionUser);
        return "redirect:/order-list";
    }
    // 삭제하기
    @PostMapping("/order/{id}/delete")
    public String delete() {
        return "";
    }
}
