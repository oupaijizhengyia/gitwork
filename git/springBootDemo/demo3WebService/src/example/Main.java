package example;

import javax.xml.ws.Endpoint;

/**
 * FileName: Main
 * Author: yeyang
 * Date: 2018/5/14 9:34
 */
public class Main {

    public static void main(String[] args){
        GetWeatherByCity getWeatherByCity = new GetWeatherByCity();
        Endpoint.publish("http://localhost:9000/getWeatherByCity",getWeatherByCity);
        System.out.println("服务端发布成功");
    }
}
