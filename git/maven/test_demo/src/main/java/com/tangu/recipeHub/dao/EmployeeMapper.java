package com.tangu.recipeHub.dao;

import com.tangu.tangucore.tcmts2.po.TanguUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("select e.id,employee_name,employee_code,use_state,role_id,rolename, is_operator,employee_password as password from employee e,sys_role s where employee_name=#{employeeName} AND employee_password=#{employeePassword} AND e.role_id=s.id AND use_state != 1")
    TanguUser login(TanguUser employee);
    @Select("select e.id,employee_name,employee_code,use_state,role_id,rolename, is_operator from employee e,sys_role s where employee_code=#{employeeCode} AND e.role_id=s.id AND use_state != 1")
    TanguUser mobileLogin(TanguUser employee);

}