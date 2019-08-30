package com.tangu.tangucore.commom.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fenglei on 10/25/17.
 * 打印每个controll 方法的执行时间
 */
@Slf4j
public class IntervalLogInterceptor extends HandlerInterceptorAdapter {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime.set(System.currentTimeMillis());
        log.info("接口性能监控-- 接口路径：{} 开始", request.getRequestURI());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("接口性能监控-- 接口路径：{}，执行时间：{}毫秒", request.getRequestURI(), (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
