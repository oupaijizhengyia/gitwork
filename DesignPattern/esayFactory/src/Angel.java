/**
 * FileName: Angel
 * Author: yeyang
 * Date: 2018/5/30 13:38
 */
public class Angel implements Shape{
    @Override
    public void draw() {
        System.out.println("angel is drawing");
    }

    @Override
    public void erase() {
        System.out.println("angel has erased");
    }
}
