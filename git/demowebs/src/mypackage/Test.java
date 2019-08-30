package mypackage;

import weather.*;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * FileName: Test
 * Author: yeyang
 * Date: 2018/5/12 13:40
 */
public class Test {
    public static void main(String[] a)throws Exception{
       /*HelloWorldService helloWorldService = new HelloWorldServiceLocator();
       HelloWorld helloWorld = helloWorldService.getHelloWorldPort();
       String re = helloWorld.sayHelloWorldFrom("zhangsna");
       System.out.println(re);*/
        WeatherWebService weatherWebService = new WeatherWebServiceLocator();
       WeatherWebServiceSoap_PortType weatherWebServiceSoap12Stub = weatherWebService.getWeatherWebServiceSoap12();
        //WeatherWebServiceSoap_BindingStub = weatherWebService.getWeatherWebServiceSoap()
        String[] result = weatherWebServiceSoap12Stub.getWeatherbyCityName("杭州");
        //String[] citys = weatherWebServiceSoap12Stub.getSupportCity("浙江");
        Arrays.asList(result).forEach(System.out::println);
        //Arrays.asList(citys).forEach(System.out::println);
    }
}
