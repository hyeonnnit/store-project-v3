package com.example.store.order;

import com.example.store.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/orders")
    public String orderList() {
        return "order/product-list";
    }

    @GetMapping("/order/{id}/detail")
    public String orderDetail() {
        return "order/product-detail";
    }

    @PostMapping("/order/{id}/update")
    public String orderUpdate() {
        return "redirect:/order/"+1;
    }

    @PostMapping("/order/{id}")
    public String order(){
        return "redirect:/";
    }

    @GetMapping("/order/{id}/update-form")
    public String orderUpdateForm() {
        return "order/product-update-form";
    }

    @PostMapping("/order/{id}/delete")
    public String orderDelete() {
        return "redirect:/orders";
    }

}
