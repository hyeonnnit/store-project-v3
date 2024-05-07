package com.example.store.cart;

import com.example.store.order.OrderRequest;
import com.example.store.order.OrderResponse;
import com.example.store.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        cartService.saveCart(id, sessionUser, reqDTO);
        return "redirect:/cart-list";
    }

    @GetMapping("/cart/{id}/save-form")
    public String cartSaveForm(@PathVariable Integer id, HttpServletRequest request){
        CartResponse.DetailDTO cart = cartService.getCartDetail(id);
        request.setAttribute("cart", cart);
        return "cart/save-form";
    }
    @PostMapping("/cart/update")
    public @ResponseBody String update(@RequestBody List<CartRequest.UpdateDTO> updateDTOList) {
        System.out.println(updateDTOList);
        cartService.updateCart(updateDTOList);
        return "구매를 진행하겠습니다.";
    }
}
