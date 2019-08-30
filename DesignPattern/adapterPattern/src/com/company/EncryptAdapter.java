package com.company;

/**
 * FileName: EncryptAdapter
 * Author: yeyang
 * Date: 2018/6/14 14:49
 */
public class EncryptAdapter implements Target{
    private SpecificEncrypt specificEncrypt = new SpecificEncrypt();
    @Override
    public String encrypt(Employee employee) {
        return specificEncrypt.MS5Encrypt(employee.getPassword());
    }
}
