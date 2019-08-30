package com.tangu.tangucore.tcmts2.util;

import com.tangu.common.util.Constant;
import com.tangu.common.util.entity.ResponseModel;
import com.tangu.tangucore.tcmts2.po.City;
import com.tangu.tangucore.tcmts2.po.Region;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String USER_NOT_FOUND = "账套、用户名或密码错误!";
    public static final String USER_NOT_COMPLETE = "登录信息不完整";

	//获取上海市的区
	public static List<Region> rangeList = new ArrayList<>();
	//获取所以的市
	public static List<City> cityList = new ArrayList<>();

	public static final String EMPTY_FIELD="emptyField";


    public static Object operation(Integer i,
        String value, String errMsg, Object obj){
      return i > 0 ? new ResponseModel(value).attr(ResponseModel.KEY_DATA, obj) :
        new ResponseModel().attr(ResponseModel.KEY_ERROR, errMsg);
    }
    
    public static Object operation(Integer i, 
        String value, String errMsg){
      return operation(i > 0, value, errMsg);
    }
    
    public static Object operation(Object i, String value, String errMsg){
      return operation(i == null, value, errMsg);
    }
    
    public static Object operation(boolean i){
      return operation( i,  Constant.OPRATION_SUCCESS, Constant.OPRATION_FAILED);
    }
    
    public static Object operation(boolean i, String value, String errMsg){
      return i ? new ResponseModel(value) :
        new ResponseModel().attr(ResponseModel.KEY_ERROR, errMsg);
    }
    public static Object operation(Integer i){
    	return operation(i, Constant.OPRATION_SUCCESS, Constant.OPRATION_FAILED);
	}


}
