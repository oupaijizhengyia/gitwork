/**
 * FileName: Square
 * Author: yeyang
 * Date: 2018/5/30 13:37
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("square is drawing");
    }

    @Override
    public void erase() {
        System.out.println("square has erased");
    }
}
