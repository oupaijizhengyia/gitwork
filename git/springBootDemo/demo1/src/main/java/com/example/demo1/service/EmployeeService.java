package com.example.demo1.service;

import com.example.demo1.po.Employee;

import java.util.List;

/**
 * FileName: EmployeeService
 * Author: yeyang
 * Date: 2018/3/27 9:50
 */
public interface EmployeeService {
    int add(Employee e);
    int delete(Integer id);
    int update(Employee e);
    List<Employee> getEmployee(Employee e);
}
