package com.example.demo4.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: JwtUtil
 * Author: yeyang
 * Date: 2018/4/24 16:50
 */
public class JwtUtil {
    private  String secret = "yysecret";

    public  String createToken(JwtUser jwtUser){
        Map<String,Object> clamis = new HashMap<>();
        clamis.put("username",jwtUser.getUsername());
        clamis.put("password",jwtUser.getPassword());

        final Date iss = new Date();//签发日期
        final Date exp = new Date(iss.getTime() + 64000 * 1000);//到期日期
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("--------------"+secret);

        return Jwts.builder()
                .setClaims(clamis)
                .setExpiration(exp)
                .setIssuedAt(iss)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public  JwtUser verifyToken(String token){
        JwtUser jwtUser = getJwtUserFromToken(token);
        return jwtUser;
    }

    private  JwtUser getJwtUserFromToken(String token) {
        JwtUser jwtUser = new JwtUser();
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        if(claims != null){
           System.out.println(claims);
            jwtUser.setPassword(claims.get("username").toString());
            jwtUser.setUsername(claims.get("password").toString());
        }
        return jwtUser;
    }

}
