package IoDH;

/**
 * FileName: Singleton
 * Author: yeyang
 * Date: 2018/6/4 13:58
 */
public class Singleton {
    private static class Inner{
        private static Singleton singleton = new Singleton();
    }
    public Singleton(){}
    public static Singleton getBean(){
        return Inner.singleton;
    }
}
