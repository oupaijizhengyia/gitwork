package encrptExample;

public abstract class Encrpt {
    /**
     * 这里使用抽象类的原因
     * 1.为了使装饰前以及装饰后的对象相同(抽象或接口)
     * 2.String不可继承(不可接口,因为要有属性)
     */
    public String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public abstract Encrpt encrpt(Encrpt e);
}
