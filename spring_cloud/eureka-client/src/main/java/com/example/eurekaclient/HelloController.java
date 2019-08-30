package com.example.eurekaclient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: HelloController
 * Author: yeyang
 * Date: 2019/4/26 17:46
 */
@RequestMapping("hello")
@RestController
public class HelloController {

    @PostMapping(value = "say")
    public Object say(){
        return "hello world";
    }
}
