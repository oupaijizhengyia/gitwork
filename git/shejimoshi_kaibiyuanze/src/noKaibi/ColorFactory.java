package noKaibi;

/**
 * FileName: ColorFactory
 * Author: yeyang
 * Date: 2018/5/28 10:40
 */
public class ColorFactory {
    public Color getColor(String name){
        if(name.equals("green")){
            return new Green();
        }else if(name.equals("red")){
            return new Red();
        }else {
            return null;
        }
    }
}
