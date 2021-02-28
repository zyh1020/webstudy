package com.zyh.webstudy.utils;



import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class JwtTokenUtil {

    // 荷载内容
    public static final String  CLAIM_KEY_USERNAME = "sub";
    public static final String  CLAIM_KEY_CREATED = "created";

    // 过期时间
    @Value("${jwt.tokeneCpiration}")
    private long tokeneCpiration;
    // 密钥
    @Value("${jwt.tokenSignKey}")
    private String tokenSignKey;
    // jwt令牌包含在头部的key
    @Value("${jwt.tokenHead}")
    private String tokenHead;



    // ①，根据用户名生成token
    public String getJwtToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userName);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    private String generateToken(Map <String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + tokeneCpiration))
                .signWith(SignatureAlgorithm.HS512,tokenSignKey)
                .compact();
    }

    // ②，根据token获取用户名
    public String getUserName(String token){
        String userName;
        try {
            Claims claims = getClaims(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            userName = null;
        }
        return userName;
    }

    // ③，判断token是否有效
    public boolean validateToken(String token,String detailsName){
        // 获取token中的用户名
        String userName = getUserName(token);
        return userName.equals(detailsName) && !isTokenExpired(token);
    }

    // ④，判断token是否过期
    public boolean isTokenExpired(String token){
        // 获取荷载中的过期时间
        Claims claims = getClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    // ⑤，判断token是否可以被刷新
    public boolean canRefreshToken(String token){
         // token是过期表示可以刷新
        return !isTokenExpired(token);
    }

    // ⑥，刷新token
    public String refreshToken(String token){
        Claims claims = getClaims(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    // 从token中获取荷载
    public Claims getClaims(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

}
