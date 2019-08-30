package com.tangu.recipeHub.service.impl;

import com.tangu.recipeHub.dao.EmployeeMapper;
import com.tangu.tangucore.tcmts2.po.TanguUser;
import com.tangu.tangucore.tcmts2.service.TanguUserService;
import com.tangu.tangucore.tcmts2.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 此处的
 */

@Service
public class EmployeeServiceImpl implements TanguUserService<TanguUser> {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public TanguUser login(String username, String password) {
        TanguUser TanguUser = new TanguUser();
        TanguUser.setEmployeeName(username);
        TanguUser.setEmployeePassword(CommonUtil.DoubleMD5(password));
        return employeeMapper.login(TanguUser);
    }

    @Override
    public TanguUser mobileLogin(String username) {
        TanguUser TanguUser = new TanguUser();
        TanguUser.setEmployeeCode(username);
        return employeeMapper.mobileLogin(TanguUser);
    }

}
