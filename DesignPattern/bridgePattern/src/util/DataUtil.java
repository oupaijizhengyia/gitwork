package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * FileName: DataUtil
 * Author: yeyang
 * Date: 2018/6/19 11:55
 */
public class DataUtil {

    public static Object getBean(String type) {
        String path = "src/config.txt";
        Object o = null;
        File file = new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> sts = bufferedReader.lines();
            Map map1 = new HashMap(2);
            sts.forEach(st->{
                String[] strArr = st.split(":");
                map1.put(strArr[0],strArr[1]);
            });
            Class c = Class.forName(map1.get(type).toString());
            o = c.newInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return o;
    }
}
