package com.tangu.order.webService;

import org.apache.axis.AxisFault;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * FileName: d
 * Author: yeyang
 * Date: 2018/5/11 17:51
 */
public class d {
    public static void main(String[] a ){
        GetWeatherByCityService service = new GetWeatherByCityServiceLocator();
        try {
            Weather weather = service.getweatherPort();
            System.out.println(weather.getWeatherByCityName("杭州",""));
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
