package com.demo;

import java.io.*;

/**
 * FileName: FileUtil
 * Author: yeyang
 * Date: 2018/6/1 11:12
 */
public class FileUtil {
    public static Object getBean(){
        Object o = new Object();
        File file = new File("src/config.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = bufferedReader.readLine();
            Class c = Class.forName(s);
            o = c.newInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
