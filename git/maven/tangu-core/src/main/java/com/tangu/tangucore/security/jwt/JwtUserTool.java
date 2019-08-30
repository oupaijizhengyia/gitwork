package com.tangu.tangucore.security.jwt;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @author:yinghuaxu
 * @Description:getUserInfo
 * @Date: create in 14:36 2017/10/24
 */
public class JwtUserTool {

    public static Integer getId(){
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }
        JwtUser jwtUser=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUser.getId().intValue();
    }

    public static String getRoleId(){
        List list=(List) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if(CollectionUtils.isNotEmpty(list)){
            SimpleGrantedAuthority auth =  (SimpleGrantedAuthority)list.get(0);
            return auth.getAuthority().replace(JwtUser.GRENT_PREFEX,"");
        }
        return null;
    }

    public static String getTenant(){
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null && principal instanceof JwtUser){
            JwtUser jwtUser=(JwtUser) principal;
            return jwtUser.getTenant();
        }
        return null;
    }
    
    public static String getUserName(){
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            return "";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null && principal instanceof JwtUser){
            JwtUser jwtUser=(JwtUser) principal;
            return jwtUser.getUsername();
        }
        return "";
    }
}

