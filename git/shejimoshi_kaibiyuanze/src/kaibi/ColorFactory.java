package kaibi;

/**
 * FileName: ColorFactory
 * Author: yeyang
 * Date: 2018/5/28 10:49
 */
public class ColorFactory {
    Class<? extends Color> c;
    Color ccc;
    public void setColor(Class<? extends Color> cc){
        c = cc;
    }
    public void getColor(){
        Color re ;
        try {
            re = c.newInstance();
            re.say();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void setCcc(Color c){
        this.ccc = c;
    }
    public void say(){
        this.ccc.say();
    }
}
