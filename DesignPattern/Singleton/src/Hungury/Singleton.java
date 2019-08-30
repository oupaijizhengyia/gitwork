package Hungury;

/**
 * FileName: Singleton
 * Author: yeyang
 * Date: 2018/6/4 13:57
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton(){}
    public static Singleton getBean(){
        return singleton;
    }
}
