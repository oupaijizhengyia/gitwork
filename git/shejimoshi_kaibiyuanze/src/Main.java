import kaibi.ColorFactory;
import kaibi.Green;

public class Main {

    public static void main(String[] args) {
      /*  ColorFactory factory = new ColorFactory();
        Green green = (Green) factory.getColor("green");
        Red red = (Red) factory.getColor("red");
        green.say();
        red.say();*/
        ColorFactory colorFactory = new ColorFactory();
        Green green = new Green();
        colorFactory.setCcc(green);
        colorFactory.say();


    }
}
