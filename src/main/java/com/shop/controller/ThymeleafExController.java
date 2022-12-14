package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "thymeleaf")
public class ThymeleafExController {

    @GetMapping(value = "/ex01")
    public String thymeleafExample(Model model){
        model.addAttribute("data", "타임리프 예제입니다");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value = "/ex07")
    public String thymeleafExample07(){
        return "thymeleafEx/thymeleafEx07";
    }
}
