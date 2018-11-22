package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.WebSecurityConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class StartController {
    @Resource
    UserService userService;

    private static Logger logger = Logger.getLogger(StartController.class);

    @GetMapping()//任何不存在的路径重定向到indexApp函数
    public String startApp(Model model) {
        logger.info("get  startApp");
        return "redirect:http://127.0.0.1:8080/start";
    }


    @GetMapping("start")//登录界面
    public String start_one(Model model) {
        logger.info("get  start_one");
        return "start";
    }

    @PostMapping("login")//登录验证，请求在start,html
    public String login(@ModelAttribute User user, HttpSession session) {
        logger.info("post  login");

        User result=userService.login(user);
        if(result==null){
            return "redirect:err";
        }else {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, result.getPhone());
            return "redirect:index";
        }
    }


    @PostMapping("register")//注册验证，请求在start,html
    public String register(@ModelAttribute User user, HttpSession session) {
        logger.info("post  register");

        int result=0;
        try {
            result = userService.addUser(user);
        }catch (Exception e){
            logger.error(e);
        }
        if(result==1){
            session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getPhone());
            return "redirect:index";
            //return "redirect:/webuser/index.html";
        }else {
            return "redirect:err";
        }
    }

    @GetMapping("index")//验证后的重定向，所以此处也应带
    public String index_one(Model model,HttpSession session) {
        logger.info("get  index_one");
        model.addAttribute("user",session.getAttribute(WebSecurityConfig.SESSION_KEY));
        return "indexApp";
    }

    @GetMapping("err")//验证后的重定向，所以此处也应带
    public String err(Model model) {
        logger.info("get  err");
        return "err";
    }

}