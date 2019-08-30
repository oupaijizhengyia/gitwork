package com.example.demo1.controller;

import com.example.demo1.bean.ResponseModel;
import com.example.demo1.po.Employee;
import com.example.demo1.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

/**
 * FileName: EmployeeController
 * Author: yeyang
 * Date: 2018/3/27 10:05
 */
@RestController
@Api(value = "员工管理",description = "管理员工")
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @ApiOperation(tags = "员工管理",value = "根据条件列出所有员工",response = Employee.class)
    @ApiImplicitParam(value = "查询条件",name = "e",dataType = "Employee")
    @RequestMapping(value = "listEmployee",method = RequestMethod.POST)
    public ResponseModel listEmployee(@RequestBody  Employee e){
        return new ResponseModel(employeeService.getEmployee(e));
    }


















}
