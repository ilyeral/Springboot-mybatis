package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping("index1")//不显示实际路径，只显示请求路径
    public String index(
            Model model) {
        return "index";
    }



    @GetMapping("index2")//重定向到index3
    public String index1(
            Model model) {
        return "redirect:index3";
    }
    @GetMapping("index3")//不显示实际路径，只显示请求路径
    public String index2(
            Model model) {
        return "err";
    }



}