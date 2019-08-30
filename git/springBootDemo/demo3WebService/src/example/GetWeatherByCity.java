package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: GetWeatherByCity
 * Author: yeyang
 * Date: 2018/5/14 9:27
 */
@WebService
public class GetWeatherByCity {

    @WebMethod
    public  String getWeatherByCityName(String city){
        Map<String,String> weatherMap = new HashMap<>(16);
        weatherMap.put("杭州","晴天");
        weatherMap.put("北京","雷阵雨");
        weatherMap.put("南宁","阴天");
        weatherMap.put("南京","多云");
        weatherMap.put("长沙","暴雨");
        weatherMap.put("珠海","暴雪");
        if(!weatherMap.containsKey(city)){
            return "暂无该城市";
        }
        return city+"的天气是"+weatherMap.get(city);
    }
}
