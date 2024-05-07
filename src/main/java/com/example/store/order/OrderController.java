package com.example.store.order;

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
    @GetMapping({"/order/{id}/save-form"})
    public String saveOrderList(@PathVariable Integer id, HttpServletRequest request, OrderRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<OrderResponse.ListDTO> orderList = orderService.getOrderList(reqDTO,sessionUser,id);
        System.out.println(orderList);
        request.setAttribute("orderList", orderList);
        return "order/save-form";
    }

    // 삭제하기
    @PostMapping("/order/{id}/delete")
    public String delete() {
        return "";
    }
}
