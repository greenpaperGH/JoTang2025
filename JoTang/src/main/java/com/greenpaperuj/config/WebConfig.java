package com.greenpaperuj.config;

import com.greenpaperuj.interceptor.LoginCheckInterceptor;
import com.greenpaperuj.interceptor.UserRoleCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final static List<String> loginAndSignup = List.of("/login", "/signup", "/hello");

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Autowired
    private UserRoleCheckInterceptor userRoleCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns(loginAndSignup);
        registry.addInterceptor(userRoleCheckInterceptor).addPathPatterns("/**").excludePathPatterns(loginAndSignup);
    }
}
