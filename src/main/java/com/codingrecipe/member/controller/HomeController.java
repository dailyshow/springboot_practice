package com.codingrecipe.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        model.addAttribute("member", loginEmail);

        return "index";
    }


}
