package bantouming;

/**
 * FileName: AddBehavior
 * Author: yeyang
 * Date: 2018/7/2 9:49
 */
public class Approver extends Decorator {
    public Approver(Document document) {
        super(document);
        System.out.println("增加审批功能");
    }
    public void approver(){
        System.out.println("我是审批功能");
    }
}
