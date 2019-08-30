package util;

import java.io.*;

/**
 * FileName: DataUtil
 * Author: yeyang
 * Date: 2018/6/4 10:38
 */
public class DataUtil {
    public static Object getBean(){
        File file = new File("src/config.txt");
        Object o = null;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String name = br.readLine();
            Class c = Class.forName(name);
            o = c.newInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
