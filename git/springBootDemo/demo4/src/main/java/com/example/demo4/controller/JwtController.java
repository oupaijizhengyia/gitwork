package com.example.demo4.controller;

import com.example.demo4.jwt.JwtUser;
import com.example.demo4.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: JwtController
 * Author: yeyang
 * Date: 2018/4/24 16:47
 */
@Api(value = "授权",description = "授权")
@RestController
@Slf4j
public class JwtController {
    private JwtUtil jwtUtil = new JwtUtil();

    @ApiOperation(value = "授权方法")
    @RequestMapping(value = "auth",method = RequestMethod.POST)
    public Object auth(@RequestBody JwtUser map){
        String s = jwtUtil.createToken(map);
        return s;
    }

    @ApiOperation(value = "验证方法")
    @RequestMapping(value = "verify",method = RequestMethod.POST)
    public Object verify(@RequestBody String token){
        return jwtUtil.verifyToken(token);
    }

}
