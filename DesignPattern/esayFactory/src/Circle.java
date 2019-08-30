/**
 * FileName: Circle
 * Author: yeyang
 * Date: 2018/5/30 13:39
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle is drawing");
    }

    @Override
    public void erase() {
        System.out.println("Circle has erased");
    }
}
