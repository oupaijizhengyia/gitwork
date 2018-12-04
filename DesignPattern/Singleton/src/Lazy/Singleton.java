package Hungury;

/**
 * FileName: Singleton
 * Author: yeyang
 * Date: 2018/6/4 13:54
 */
public class Singleton {
    private Singleton singleton;
    private Singleton(){}

    public Singleton getBean(){
        if(singleton == null){
            return new Singleton();
        }else {
            return singleton;
        }
    }

}
