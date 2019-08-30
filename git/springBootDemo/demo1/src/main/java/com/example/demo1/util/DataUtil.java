package com.example.demo1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: DataUtil
 * Author: yeyang
 * Date: 2018/3/27 10:00
 */
public class DataUtil {
    public static String dataFormate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        String re = sdf.format(date);
        return re;
    }
}
