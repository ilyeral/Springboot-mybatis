package com.example.demo.utils;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{
    private static Logger logger = Logger.getLogger(WebSecurityConfig.class);
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        // 排除配置
        //addInterceptor.excludePathPatterns("/index1");
        addInterceptor.excludePathPatterns("/start");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/register");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            logger.info(request.getRequestURL());
            String[] arr=request.getRequestURL().toString().split("/");
            for(String aa:arr ){
                logger.info("*"+aa+"*");
            }
            HttpSession session = request.getSession();

            if(session.getAttribute(SESSION_KEY) != null) {
                if (arr.length>=4) {
                    if (arr[3].equals("index")) {
                        return true;
                    }
                }
                String url = "/index";
                response.sendRedirect(url);
                return false;
            }

//            if(arr.length>=4){
//                if(arr[3].equals("start")){
//                    return true;
//                }
//            }
            // 跳转登录
            String url = "/start";
            response.sendRedirect(url);
            return false;
        }
    }
}