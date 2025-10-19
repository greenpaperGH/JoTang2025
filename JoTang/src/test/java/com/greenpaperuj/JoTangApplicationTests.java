package com.greenpaperuj;

import com.greenpaperuj.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@SpringBootTest
class JoTangApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testJWT() {
        Map<String , Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "JHC");

        String jwt = JwtUtils.generateJwt(claims);

        Claims claims1 = JwtUtils.parseJwt(jwt);
        String name = claims1.get("name", String.class);
        System.out.println(name);
    }

}
