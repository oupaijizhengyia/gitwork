/**
 * FileName: Pic
 * Author: yeyang
 * Date: 2018/7/10 10:19
 */
public class Pic extends Example {
    @Override
    public void display(Out out) {
        System.out.println("我是Pic格式,位置为:("+out.getX()+","+out.getY()+"),大小为"+ out.getSize());
    }
}
