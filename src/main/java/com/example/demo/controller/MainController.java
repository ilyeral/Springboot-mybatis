package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("index")
public class MainController {
    @Resource
    UserService userService;

    private static Logger logger = Logger.getLogger(StartController.class);

    @GetMapping("/aaa")//任何不存在的路径重定向到indexApp函数
    public String startApp(Model model) {
        logger.info("get  startApp");
        return "redirect:indexApp";
    }
}
