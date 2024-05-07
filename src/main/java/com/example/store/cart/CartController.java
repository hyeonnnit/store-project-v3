package com.example.store.cart;

import com.example.store.order.OrderRequest;
import com.example.store.order.OrderResponse;
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
public class CartController {
    private final CartService cartService;
    private final HttpSession session;

    // 장바구니 목록
    @GetMapping({"/cart-list"})
    public String list(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<CartResponse.ListDTO> cartList = cartService.getCartList(sessionUser.getId());
        request.setAttribute("cartList", cartList);
        return "cart/product-list";
    }

    // 장바구니 담기
    @PostMapping("/cart/{id}/save")
    public String cartSave(@PathVariable Integer id, CartRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        cartService.cartSaveProduct(id, sessionUser, reqDTO);
        return "redirect:/cart-list";
    }

    @GetMapping("/cart/{id}/save-form")
    public String cartSaveForm(@PathVariable Integer id, HttpServletRequest request){
        CartResponse.DetailDTO cart = cartService.getCartDetail(id);
        request.setAttribute("cart", cart);
        return "cart/save-form";
    }

    // 삭제하기 - 주문하면 삭제되고 어디로 반환돼야 하는지 모르겠어요
    @PostMapping("/cart/{id}/delete")
    public String delete() {
        return "";
    }
}
