package com.example.store.order;

import com.example.store.product.Product;
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
        List<OrderResponse.ListDTO> orderList = orderService.getOrderList(sessionUser.getId());
        request.setAttribute("orderList", orderList);
        return "order/product-list";
    }

    @GetMapping("/order/{id}/detail")
    public String orderDetail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OrderResponse.DetailDTO order = orderService.getOrderDetail(id, sessionUser);
        request.setAttribute("order",order);
        return "order/product-detail";
    }

    @PostMapping("/order/{id}/update")
    public String orderUpdate(@PathVariable Integer id, OrderRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Order order = orderService.editProduct(sessionUser.getId(),reqDTO);
        session.setAttribute("newOrder", order );
        return "redirect:/orders";
    }

    @GetMapping("/order/{id}/update-form")
    public String orderUpdateForm(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OrderResponse.DetailDTO order = orderService.getOrderDetail(id, sessionUser);
        request.setAttribute("order", order);
        return "order/product-update-form";
    }

    @GetMapping("/order/{id}/product-form")
    public String orderForm(@PathVariable Integer id, HttpServletRequest request){
        Product product = orderService.getOrderProduct(id);
        request.setAttribute("product", product);
        return "order/product-order-form";
    }

    @PostMapping("/order/{id}/product")
    public String order(@PathVariable Integer id, OrderRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        orderService.orderSaveProduct(id, sessionUser, reqDTO);
        return "redirect:/";
    }

    @PostMapping("/order/{id}/delete")
    public String orderDelete(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

}
