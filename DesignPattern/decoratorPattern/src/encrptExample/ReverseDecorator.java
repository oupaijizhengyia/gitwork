package encrptExample;

/**
 * FileName: ReverseDecorator
 * Author: yeyang
 * Date: 2018/7/2 15:24
 */
public class ReverseDecorator extends StringDecorator {
    public ReverseDecorator(Encrpt encrpt) {
        super(encrpt);
    }

    @Override
    public Encrpt encrpt(Encrpt encrpt) {
        super.encrpt(encrpt);
        return reverseEncrpt(encrpt);
    }

    private Encrpt reverseEncrpt(Encrpt encrpt) {
        System.out.println("开始逆向加密********************");
        System.out.println("加密前: "+encrpt.getS()+"  加密后 : 我是逆向加密后的字符");
        String s = "我是逆向加密后的字符";
        encrpt.setS(s);
        return encrpt;
    }
}
