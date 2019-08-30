package com.tangu.tangucore.tcmts2.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tangu.tangucore.security.exception.PermissionDeniedException;
import com.tangu.tangucore.security.jwt.JwtService;
import com.tangu.tangucore.security.jwt.JwtUser;
import com.tangu.tangucore.tcmts2.po.TanguUser;
import com.tangu.tangucore.tcmts2.service.TanguUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 实现security里的接口
 * @author fenglei on 8/29/17.
 */
@Service
public class JwtServiceImpl implements JwtService {
    @Autowired
    TanguUserService employeeService;

    private final Integer NOT_OPERATOR = 0;
    
    @Override
    public UserDetails auth(String username, String password) throws UsernameNotFoundException, PermissionDeniedException {
        return auth("",username,password);
    }

    @Override
    public UserDetails auth(String tenant, String username, String password) throws UsernameNotFoundException,PermissionDeniedException{
        if(password == null){
            password = "";
        }
        Object obj = employeeService.login(username,password);
        TanguUser user = JSONObject.parseObject(JSONObject.toJSONString(obj),TanguUser.class);
        if(user == null ){
            throw new UsernameNotFoundException(String.format("No user found with useranme '%s' password '%s'",username, password));
        }
        if (NOT_OPERATOR.equals(user.getIsOperator())){
            throw new PermissionDeniedException("PermissionDenied");
        }
        UserDetails userDetails = create(user, tenant);
        return userDetails;
    }

    @Override
    public String refresh(String oldtoken){
        return oldtoken;
    }

    private  JwtUser create(TanguUser employee, String tenant){
        return new JwtUser( employee.getId().longValue(),
                tenant,
                employee.getEmployeeCode(),
                employee.getEmployeePassword(),
                ""+employee.getRoleId(),
                true,
                new Date());
    }
}
