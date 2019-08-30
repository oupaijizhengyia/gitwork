/**
 * FileName: Main
 * Author: yeyang
 * Date: 2019/8/24 16:47
 */
public class Test1 {
    public static void main(String[] args) {
        Smoke smoke = new Smoke();
        smoke.setName("宇宙");
        smoke.setPrice(2);

        EarthSomkeProxy earthSomkeProxy = new EarthSomkeProxy(smoke);
        earthSomkeProxy.sell();
    }
}
