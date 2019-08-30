package encrptExample;

/**
 * FileName: MoveDecorator
 * Author: yeyang
 * Date: 2018/7/2 15:15
 */
public class MoveDecorator extends StringDecorator {
    public MoveDecorator(Encrpt encrpt) {
        super(encrpt);
    }

    @Override
    public Encrpt encrpt(Encrpt encrpt){
        super.encrpt(encrpt);
        encrpt = this.moveEncrpt(encrpt);
        return encrpt;
    }
    public Encrpt moveEncrpt(Encrpt encrpt){
        System.out.println("开始调用了移位加密********************");
        System.out.println("加密前: "+encrpt.getS()+"  加密后 : 我是移位加密后的字符");
        String s = "我是移位加密后的字符";
        encrpt.setS(s);
        return encrpt;
    }
}
