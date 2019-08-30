package com.example.demo4.jwt;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName: JwtUser
 * Author: yeyang
 * Date: 2018/4/24 16:54
 */
@Data
public class JwtUser implements Serializable{
    private String username;
    private String password;
}
