/**
 * FileName: Gif
 * Author: yeyang
 * Date: 2018/7/10 10:26
 */
public class Gif extends Example {
    @Override
    public void display(Out out) {
        System.out.println("我是GIF格式,位置为:("+out.getX()+","+out.getY()+"),大小为"+ out.getSize());
    }
}
