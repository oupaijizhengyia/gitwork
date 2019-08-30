package bantouming;

/**
 * FileName: Deleter
 * Author: yeyang
 * Date: 2018/7/2 10:06
 */
public class Deleter extends Decorator {
    public Deleter(Document document) {
        super(document);
        System.out.println("增加删除功能");
    }
    public void delete(){
        System.out.println("使用删除功能");
    }
}
