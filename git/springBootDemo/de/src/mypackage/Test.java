package mypackage;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * FileName: Test
 * Author: yeyang
 * Date: 2018/5/14 9:41
 */
public class Test {
    public static void main(String[] args) throws ServiceException {
        GetWeatherByCityService getWeatherByCityService = new GetWeatherByCityServiceLocator();
        GetWeatherByCity re = getWeatherByCityService.getGetWeatherByCityPort();
        try {
            String result = re.getWeatherByCityName("珠海");
            System.out.println(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
