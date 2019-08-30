package com.tangu.tangucore.security.config;

import com.tangu.tangucore.security.jwt.JwtAuthenticationEntryPoint;
import com.tangu.tangucore.security.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author fenglei on 8/29/17.
 */
@SuppressWarnings({"SpringJavaAutowiringInspection", "AlibabaAvoidCommentBehindStatement"})
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Value("${jwt.route.authentication.path}")
    private String authPath;

    @SuppressWarnings("AlibabaAvoidCommentBehindStatement")
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //noinspection AlibabaAvoidCommentBehindStatement
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .formLogin().disable()
                //don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/docs/*",
                        "/img/*",
                        "/**/*.json",
                        "/*.html",
                        "/**/*.html",
                        "/*.ico",
                        "**/*.html",
                        "/**/*.css",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/"+authPath+"**").permitAll()
                .antMatchers("/"+authPath+"/**").permitAll()
                .antMatchers("/service/**").permitAll()
                .antMatchers("/v2/api-docs",//swagger api json
                        "/swagger-resources/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URI
                        "/swagger-resources/configuration/security",//安全选项
                        "/swagger-ui.html").permitAll();
        unAuthorizePath(httpSecurity);
        httpSecurity.authorizeRequests().anyRequest().authenticated();


                //end


        //Custom Jwt based security filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //disable page caching
        httpSecurity.headers().cacheControl();
    }

    /**
     * 配置免授权路径
     * @param httpSecurity
     */
    public void unAuthorizePath(HttpSecurity httpSecurity) throws Exception{};
}
