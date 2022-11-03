package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String main(){
        return "common/index";
    }
    @GetMapping("/common/about")
    public String about(){
        return "common/about";
    }
    @GetMapping("/common/category")
    public String category(){
        return "common/category";
    }
    @GetMapping("/common/contact")
    public String contact(){
        return "common/contact";
    }
    @GetMapping("/common/search-result")
    public String searchresult(){
        return "common/search-result";
    }
    @GetMapping("/common/single-post")
    public String singlepost(){
        return "common/single-post";
    }

    @GetMapping("member/kakao_login")
    public String kakaologin(){
        return "member/kakao_login";
    }
}
