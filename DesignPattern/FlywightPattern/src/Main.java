public class Main {

    public static void main(String[] args) {
        Out out = new Out(1,2,3);
        Out out2 = new Out(2,3,4);
        Factory factory = Factory.getFactory();
        Factory factory1 = Factory.getFactory();
        Example pic = factory.getExample("Pic");
        Example pic2 = factory.getExample("Pic");
        System.out.println(factory==factory1);
        System.out.println(pic==pic2);
        pic.display(out);
        pic2.display(out2);

    }
}
