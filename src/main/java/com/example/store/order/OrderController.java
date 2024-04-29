package com.example.store.order;

import com.example.store.user.User;
import com.example.store.user.UserService;
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

    @GetMapping("/orders")
    public String orderList(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<OrderResponse.OrderDTO> orderList = orderService.getOrderList(sessionUser.getId());
        request.setAttribute("orderList", orderList);
        return "order/product-list";
    }

    @GetMapping("/order/{id}/detail")
    public String orderDetail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OrderResponse.OrderDTO order = orderService.getOrderProduct(id, sessionUser.getId());
        request.setAttribute("order",order);
        return "order/product-detail";
    }

    @PostMapping("/order/{id}/update")
    public String orderUpdate(@PathVariable Integer id, OrderRequest.UpdateDTO reqDTO) {
        orderService.editProduct(id, reqDTO);
        return "redirect:/order/"+id+"detail";
    }

    @GetMapping("/order/{id}/update-form")
    public String orderUpdateForm(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Order order = orderService.getOrder(id, sessionUser.getId());
        request.setAttribute("order", order);
        return "order/product-update-form";
    }

    @PostMapping("/order/{id}")
    public String order(){
        return "redirect:/";
    }

    @PostMapping("/order/{id}/delete")
    public String orderDelete() {
        return "redirect:/orders";
    }

}
