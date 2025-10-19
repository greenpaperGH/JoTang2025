package com.greenpaperuj.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private final static String signKey = "afbyuknavbrbucnfucrakbynecgbfyeaknuecnfcuyaeauybkcykaeucybangfygskuay" +
            "auecuelicnegfhuaeilfhhufcaeihiauecruielhuahcufeuiacfiahiuecfauruailecrauycrbnauycagycrgyukayaeilcy" +
            "aukecryeukuilrieaaekhcrueajjjjjjjjcraekrucralucehraueilcruaeurceuakuicgrueaekcrmauircrmeiucnaeiecru" +
            "kaeuaeruilrrycuiaenuilrcyaeuiuucrhnauiaesrearuioernauireiarciacrynyutroicuaippcre";
    private final static Long expire = 43200000L;

    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
