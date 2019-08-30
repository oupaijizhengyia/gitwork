package com.tangu.recipeHub.common;

import com.tangu.tangucore.security.config.WebSecurityConfig;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * FileName: b
 * Author: yeyang
 * Date: 2018/8/8 12:23
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityconfiguration extends WebSecurityConfig {
}
