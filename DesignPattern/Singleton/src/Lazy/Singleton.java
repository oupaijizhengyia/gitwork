package Lazy;

/**
 * FileName: Singleton
 * Author: yeyang
 * Date: 2018/6/4 13:54
 */
public class Singleton {
    private static Singleton singleton;
    private Singleton(){}

    public static Singleton getBean(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

}
