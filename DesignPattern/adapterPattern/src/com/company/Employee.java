package com.company;

/**
 * FileName: Employee
 * Author: yeyang
 * Date: 2018/6/14 14:43
 */
public class Employee {
    private String username;
    private String password;
    public Employee(String username,String password){
        this.username =username;
        this.password = password;
    };
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
