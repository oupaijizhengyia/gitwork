package com.tangu.tangucore.security.config;

import com.tangu.tangucore.security.jwt.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: bean
 * Author: yeyang
 * Date: 2018/8/8 12:09
 */
@Configuration
public class bean {
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
        return new JwtAuthenticationTokenFilter();
    }
}
