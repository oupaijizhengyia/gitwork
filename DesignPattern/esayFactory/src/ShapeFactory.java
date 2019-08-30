/**
 * FileName: ShapeFactory
 * Author: yeyang
 * Date: 2018/5/30 13:39
 */
public class ShapeFactory {

    public Shape getShape(String type){
        Shape shape = null;
        if("angel".equals(type)){
            shape = new Angel();
        }else if("circle".equals(type)){
            shape = new Circle();
        }else if("square".equals(type)){
            shape = new Square();
        }else {
            try {
                throw new UnSupportShapeException();
            } catch (UnSupportShapeException e) {
                e.printStackTrace();
            }
        }
        return shape;
    }
}
