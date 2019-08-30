package com.example.demo1.bean;

import java.util.HashMap;

/**
 * FileName: ResponseModel
 * Author: yeyang
 * Date: 2018/3/27 10:12
 */
public class ResponseModel extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;
    public static final String KEY_DATA = "data";
    public static final String KEY_MESSAGE = "feedbackMsg";

    public ResponseModel(){}

    public ResponseModel(Object obj){
        this.put("data",obj);
    }
    public ResponseModel(String data,Object obj){
        this.put(data,obj);
    }
    public ResponseModel(String message) {
        this.put("feedbackMsg", message);
    }

    public ResponseModel attr(String data,Object obj){
        this.put(data,obj);
        return this;
    }











}
