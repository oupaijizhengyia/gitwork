package com.tangu.tangucore.tcmts2.service;

public interface TanguUserService<T>{
    T login(String username, String password);
    T mobileLogin(String username);
}
