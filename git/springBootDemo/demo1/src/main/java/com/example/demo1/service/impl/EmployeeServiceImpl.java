package com.example.demo1.service.impl;

import com.example.demo1.dao.EmployeeMapper;
import com.example.demo1.po.Employee;
import com.example.demo1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: EmployeeServiceImpl
 * Author: yeyang
 * Date: 2018/3/27 9:58
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public int add(Employee e) {
        return employeeMapper.insert(e);
    }

    @Override
    public int delete(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Employee e) {
        return employeeMapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public List<Employee> getEmployee(Employee e) {
        return employeeMapper.getAllByCondition(e);
    }
}
