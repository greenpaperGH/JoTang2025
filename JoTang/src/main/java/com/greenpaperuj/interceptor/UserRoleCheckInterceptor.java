package com.greenpaperuj.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenpaperuj.pojo.Product;
import com.greenpaperuj.pojo.ProductOrder;
import com.greenpaperuj.pojo.User;
import com.greenpaperuj.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Component
@Order(2)
public class UserRoleCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String url = request.getRequestURI();
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);

//        try {
//            Product product = parseJsonFromRequest(request, Product.class);
//            System.out.println("解析到: " + product.getPublisherId());
//        } catch (Exception e) {
//            System.err.println("解析失败: " + e.getMessage());
//        }
//
//        try {
//            ProductOrder productOrder = parseJsonFromRequest(request, ProductOrder.class);
//            System.out.println("解析到: " + productOrder.getProductId());
//        } catch (Exception e) {
//            System.err.println("解析失败: " + e.getMessage());
//        }
//
//        String role = claims.get("role").toString();
//        Long id = (Long) claims.get("id");
//
//        if(role.equals("ADMIN")) {
//            log.info("管理员，放行");
//            return true;
//        }
//
//        if(url.contains("products")) {
//            if(method.equals("PUT")) {
//
//            } else if(method.equals("DELETE")) {
//
//            } else if(method.equals("POSt")) {
//
//            }
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }

    public <T> T parseJsonFromRequest(HttpServletRequest request, Class<T> clazz) {
        try {
            // 获取请求体内容
            String body = getRequestBody(request);
            if (body == null || body.trim().isEmpty()) {
                throw new IllegalArgumentException("请求体为空");
            }

            // 使用 Jackson 解析 JSON
            return objectMapper.readValue(body, clazz);
        } catch (IOException e) {
            throw new RuntimeException("解析JSON失败: " + e.getMessage(), e);
        }
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
