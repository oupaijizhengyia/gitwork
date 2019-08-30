import IoDH.Singleton;

public class Main {

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getBean();
        Singleton singleton2 = Singleton.getBean();
        System.out.println(singleton1==singleton2);
    }
}
