package com.example.eurekaclientribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * FileName: CusumerHelloController
 * Author: yeyang
 * Date: 2019/4/26 18:48
 */
@RequestMapping(value = "/cusumer")
@RestController
public class CusumerHelloController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "/shu")
    public Object sayHello(){
        return restTemplate.getForObject("http://eureka-clien/hello/say",String.class);
    }
}
