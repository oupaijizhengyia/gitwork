package com.tangu.tangucore.tcmts2.util;

import com.alibaba.fastjson.JSONObject;
import com.tangu.common.util.DateUtil;
import com.tangu.common.util.MD5;
import com.tangu.tangucore.security.jwt.JwtUserTool;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by djl on 2017/11/17.
 */
public class CommonUtil {
    /* 深复制 */
    public static Object deepClone(Object source) {

        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(bos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			oos.writeObject(source);
		} catch (IOException e) {
			e.printStackTrace();
		}

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(bis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ois!=null){
			try {
				return ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
    }
	//设置双重MD5
	public static String DoubleMD5(String password){
    	password = MD5.encrypt(MD5.encrypt(password));
    	return password;
	}
	
	public static boolean isTrue(Object config){
		if(config==null){
			return false;
		}
		if(StringUtils.isBlank(config.toString())){
			return false;
		}
		if("Y".equals(config)){
			return true;
		}
		return false;
	}

	public static Map<String,Object> beanToMap(Object bean){
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					params.put(name, propertyUtilsBean.getNestedProperty(bean, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}


	//base64字符串转化成图片
  public static boolean encodeStr(String plainText, String fileName){  
    byte[] b= Base64.decodeBase64(plainText
        .replace("data:image/png;base64,","")
        .replace("data:image/jpeg;base64,","")
        .replace("data:image/gif;base64,",""));
    return saveFile(b, fileName);
  }
  
  public static boolean saveFile(byte[] b ,String fileName){
    File file = new File(fileName);
    if(!file.getParentFile().exists()){  
        file.getParentFile().mkdirs();  
    } 
    try {
      FileOutputStream write = new FileOutputStream(file);
      // Base64解码
      for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {// 调整异常数据
          b[i] += 256;
        }
      }
      write.write(b);
      write.flush();
      write.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public static boolean deleteFile(String fileName) {
    File file = new File(fileName);
    // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
    if (file.exists() && file.isFile()) {
        if (file.delete()) {
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }
    return true;
  }
  
  public static String getFilePath(String imgPath, String fileName){
    return getFilePath(imgPath, fileName, File.separator);
  }
  
  public static String getFilePath(String imgPath, String fileName, String separator){
    StringBuffer filePath = new StringBuffer();
    filePath.append(separator)
      .append(JwtUserTool.getTenant())
      .append(separator)
      .append(imgPath)
      .append(separator)
      .append(fileName);
    return filePath.toString();
  }
  
  public static String getFileName(){
    StringBuffer fileName = new StringBuffer();
    fileName.append("Tenant-")
      .append(JwtUserTool.getTenant())
      .append("Role-")
      .append(JwtUserTool.getRoleId())
      .append("-")
      .append(UUID.randomUUID().toString())
      .append(".jpg");
    return fileName.toString();
  }
  
  public static String getSampleFileName(){
	    ;
	    StringBuffer fileName = new StringBuffer();
	    fileName
	      .append(DateUtil.getDate(new Date(), "yyMM"))
	      .append("/")
	      .append(UUID.randomUUID().toString())
	      .append(".jpg");
	    return fileName.toString();
	  }
  
  public static boolean isSendSms(String processList,String process){
	  if(StringUtils.isBlank(processList)){
		  return false;
	  }
	  List<String> states = Arrays.asList(processList.split(","));
	  return states.stream().anyMatch(e->e.equals(process));
  }
  
	/**
	 * 自动生成单号
	 * @param code 初始单号 可为空
	 * @return
	 */
	public static String generatorBillCode(String code){
		code = StringUtils.trimToNull(code) == null
				? new SimpleDateFormat("yyMMdd").format(new Date())+"0001"
				: String.valueOf(Long.valueOf(code)+1);
		return code;
	}
	/**
	 * 把一个list
	 * 转换成另一个
	 *
	 * */
	public static <E,T>List<T> convertList(List<E> list){
		List<T> re = new ArrayList<>();
		list.stream()
				.forEach(wsd->{
					String wsdos = JSONObject.toJSONString(wsd);
					Object o =JSONObject.parse(wsdos);
					re.add((T)o);
				});
		return re;
	}
	/**
	 * 对象转xml
	 * @param obj
	 * @return
	 */
	public static String convertToXml(Object obj) {
		// 创建输出流
		StringWriter sw = new StringWriter();
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			marshaller.marshal(obj, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	private static final Date r1900 = new Date(-2209017600000L);
	public static String getDateFrom1900(Integer days){
		days = days - 2;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(r1900);
		calendar.add(Calendar.DAY_OF_YEAR,days);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String re = sdf.format(date);
		return re;
	}
}

