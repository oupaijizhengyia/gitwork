package encrptExample;

/**
 * FileName: StringDecorator
 * Author: yeyang
 * Date: 2018/7/2 15:08
 */
public class StringDecorator extends Encrpt{
    private Encrpt encrpt;
    public StringDecorator(Encrpt encrpt){
        this.encrpt = encrpt;
    }
    @Override
    public Encrpt encrpt(Encrpt s) {
        return this.encrpt.encrpt(s);
    }
}
