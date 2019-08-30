import factory.SystemFactory;
import util.DataUtil;

public class Main {

    public static void main(String[] args) {
        SystemFactory systemFactory = (SystemFactory)DataUtil.getBean();
        systemFactory.operate();
    }
}
