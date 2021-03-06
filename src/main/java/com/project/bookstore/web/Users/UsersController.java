package com.project.bookstore.web.Users;

import com.project.bookstore.service.orders.OrdersService;
import com.project.bookstore.service.users.AddrService;
import com.project.bookstore.service.users.CardService;
import com.project.bookstore.service.users.UsersService;
import com.project.bookstore.session.UsersInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UsersController {
    private final AddrService addrService;
    private final CardService cardService;
    private final UsersService usersService;
    private final OrdersService ordersService;
    private final UsersInfo usersInfo;

    // 회원가입
    @GetMapping("/users/signUp")
    public String signUp() {
        return "Users/signUp";
    }

    // 로그인화면
    @GetMapping("/users/signIn")
    public String signIn() {
        return "Users/signIn";
    }

    // 마이페이지
    @GetMapping("/users/mypage")
    public String mypage(Model model) {
        if (usersInfo.getUserId() != null) {
            if (usersInfo.getUserId().equals("master")) {
                model.addAttribute("master", usersService.findAllUsers(usersInfo));
            }
        }
        model.addAttribute("usersInfo", usersService.findAllUsers(usersInfo));
        model.addAttribute("addrInfo", addrService.findAddr(usersInfo));
        model.addAttribute("cardInfo", cardService.findCard(usersInfo));
        model.addAttribute("orderInfo", ordersService.ordersInfo());
        return "Users/mypage";
    }
}
