package com.example.store.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    //로그인
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        UserResponse.LoginDTO sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }


    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    //회원가입
    @PostMapping("/join")
    public String userJoin(UserRequest.JoinDTO reqDTO) {
        userService.join(reqDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/join-form")
    public String userJoinForm() {
        return "user/join-form";
    }

    @PostMapping("/update")
    public String userUpdate() {
        return "redirect:/login-form";
    }

    @GetMapping("/update-form")
    public String userUpdateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.getUser(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
