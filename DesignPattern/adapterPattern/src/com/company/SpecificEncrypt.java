package com.company;

/**
 * FileName: SpecificEncrypt
 * Author: yeyang
 * Date: 2018/6/14 14:39
 */
public class SpecificEncrypt {
    public String MS5Encrypt(String s){
        int hashvalue = 3;
        byte[] bytes = s.getBytes();
        for (int i = 0;i<bytes.length;i++){
            hashvalue *= bytes[i] << 2;
        }
        hashvalue = (hashvalue << 2) % 16;
        return re+"";
    }
}
