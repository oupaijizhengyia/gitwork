package com.tangu.tangucore.tcmts2.util.excel;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class OperationStringModel {
	static Logger logger = Logger.getLogger(OperationStringModel.class.getName());
 /*
  * 处理字符串运算式为数组
  */
 public String[] doOperationModelStr(String modelStr){
  modelStr = modelStr + "#"; //加上标识
  int strLen = modelStr.length();
  String[] strs = new String[strLen];
  String number = "";//组合的数值
  int j = 0;
  for(int i = 0;i < strLen;i++){
   String word = modelStr.substring(i,i+1);
   //将连续的数字放组成在一个数组里
   try{
    Integer.parseInt(word);//通过是否可以转为数字来判断是否为数字
    number += word;
   }catch(Exception ex){
    if(!".".equals(word)){//如果不是.
     if(number!=""){
      strs[j] = number;//组合的数值
      j++;
      strs[j] = word;//非数字符号
      number = "";
     }else{
      String word2 = modelStr.substring(i,i+1);//得到后一个字符
      //将两字符比较，如果两都是运算符‘-’(减)或第一个就是'-'；则说明后一个是负数
      if((word.equals(word2)&&"-".equals(word2))||(i==0&&"-".equals(word)))
      {
       number += "-";
       j--;
      }else{
       strs[j] = word;
      }
     }
     j++;
    }else{//如果是.、说明是小数
     number += word;
    }
   }
  }
  return strs;
 }
 
 /*
  * 计算简单的纯运算式(不带任何域符号)的结果
  * modelStr格式：Num1 运算符 Num2  如3+4
  */
 public String operationSimpleResult(String modelStr){
  String[] strs = doOperationModelStr(modelStr);
  double result = 0;
  try{
   double num1 = Double.parseDouble(strs[0].toString());
   String operation = strs[1].toString();
   double num2 = Double.parseDouble(strs[2].toString());
   if("+".equals(operation)){
    result = num1 + num2;
   }else if("-".equals(operation)){
    result = num1 - num2;
   }else if("*".equals(operation)){
    result = num1 * num2;
   }else if("/".equals(operation)){
	   if(num2==0)
	   {
		   result=0;
	   }
	   else
	   {
		   result = num1 / num2;
	   }
   }else{
    result = Double.parseDouble(modelStr);
   }
  }catch (Exception e) {//如果有异常，返回原字符串
   result =Double.parseDouble(modelStr);
  }
//  return String.valueOf(result);
  return BigDecimal.valueOf(Double.valueOf(result)).toPlainString();
 }
 
 /*
  * 通过乘、除运算符截取出简单的纯运算式
  */
 public String createNewModelByChengChuSign(String modelStr){
  String[] strs = doOperationModelStr(modelStr); //转为数组
  for(int i = 0;i<strs.length;i++ ){
   if("*".equals(strs[i])||("/".equals(strs[i]))){
	   String temp = strs[i-1]+ strs[i]+strs[i+1];
    String result = operationSimpleResult(temp); //算出结果
    modelStr = modelStr.replace(temp, result); //将结果替换原运算式
    //递归循环计算....
    modelStr = createNewModelByChengChuSign(modelStr);
   }
  }
  return modelStr;
 }
 
 /*
  * 通过加、减运算符截取出简单的纯运算式
  */
 public String createNewModelByJiaJianSign(String modelStr){
  String[] strs = doOperationModelStr(modelStr); //转为数组
  for(int i = 0;i<strs.length;i++ ){
   if("+".equals(strs[i])||("-".equals(strs[i]))){
    String temp = strs[i-1]+ strs[i]+strs[i+1];
    String result = operationSimpleResult(temp); //算出结果
    modelStr = modelStr.replace(temp, result); //将结果替换原运算式
    //递归循环计算....
    modelStr = createNewModelByJiaJianSign(modelStr);
   }
  }
  return modelStr;
 }
 
 /*
  * 处理复杂的纯运算式(不带任何域符号)的结果
  */
 public String operationHardResult(String modelStr){
  String[] strs = doOperationModelStr(modelStr);
  
  int strLen = 0;//数组的实际长度。即去null之后的长度
  for(int i=0;i<strs.length;i++){
   if(strs[i]!=null&&!"#".equals(strs[i])){
    strLen ++;
   }
  }
  if(strLen<4){//简单的纯运算式
   modelStr = operationSimpleResult(modelStr);
  }else{//复杂的纯运算式
   
   //乘除优先计算
   modelStr = createNewModelByChengChuSign(modelStr);
   //加减顺序计算
   modelStr = createNewModelByJiaJianSign(modelStr);
  }
  return modelStr;
 }
 
 /*
  * 截取出不含符号的纯运算式
  * sign1,sign2域符号
  */
 public String createNewModelNoSign(String modelStr,String sign1,String sign2){
  int left = modelStr.lastIndexOf(sign1);//最里面的运算式
  String newModel = "";
  if(left>-1){
   String newModelStr = modelStr.substring(left,modelStr.length());
   int right = newModelStr.indexOf(sign2);
   String smallModel = newModelStr.substring(1,right);//截取出符号Sign内的运算式
   String result = operationHardResult(smallModel); //将符号Sign内的运算式的值计算出来
   newModel = modelStr.replace(sign1+smallModel+sign2, result);//将运算式直接换为结果
  }
  return newModel;
 }
 
 /*
  * 循环去域符号方法
  * sign1,sign2域符号
  */
 public String outSignModel(String modelStr,String sign1,String sign2){
  //调用方法得到字符串分解后的数组
  String[] modelStrs = doOperationModelStr(modelStr);
  for(int x = 0;x < modelStrs.length;x++){
   String word = modelStrs[x];
   if(word!=null&&!"#".equals(word)){//将得到的数组去无效值，如Null，#
    if(sign1.equals(word)){
     String newModel = createNewModelNoSign(modelStr,sign1,sign2);
     modelStr = newModel;
    }
   }
  }
  return modelStr;
 }
 
 /*
  * 通过域符号得到最终的简单运算式
  */
 public String createModelBySign(String modelStr){
  
  modelStr = outSignModel(modelStr, "(", ")");//先算小括号里的值，得到去小括号后的运算式
  modelStr = outSignModel(modelStr, "[", "]");//再算中括号里的值，得到去中括号后的运算式
  //modelStr = outSignModel(modelStr, "{", "}");//最后算大括号里的值，得到去大括号后的运算式
  return modelStr;
 }
 
 /*
  * 计算字符串的值
  */
 public Double operationResult(String modelStr){
//  logger.info("modelStr==="+modelStr);
  String result = "";
  try{
   String Model = createModelBySign(modelStr); //调用方法将字符串最终转为最简单的运算式
//   logger.info("Model==="+Model);
   result =operationHardResult(Model); //返回结果
//   logger.info("result==="+result);
  }catch (Exception e) {
	  e.printStackTrace();
   result = "不是个合法的运算式！";
  }
  return format(Double.parseDouble(result));
 }
 public static Double format(Double num){
		DecimalFormat formater = new DecimalFormat("#0.##");
		return Double.valueOf(formater.format(num));
	}
}

