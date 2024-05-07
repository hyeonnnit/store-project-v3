package com.example.store.orderItem;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final HttpSession session;

    // 주문 결과 목록
    @GetMapping({"/item-list"})
    public String list() {
        return "";
    }

    // 주문 결과 전송
    @PostMapping("/item/{id}")
    public String order() {
        return "redirect:/item-list";
    }
}
