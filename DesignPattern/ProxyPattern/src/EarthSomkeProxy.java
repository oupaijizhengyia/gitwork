/**
 * FileName: EarthSomkeProxy
 * Author: yeyang
 * Date: 2019/8/24 16:53
 */
public class EarthSomkeProxy {
    /**
     * 代理的香烟
     * 这里为啥需要持有smoke 最简单的道理  香烟的代理人 总得有香烟吧
     */
    private Smoke smoke;

    public EarthSomkeProxy(Smoke smoke){
        this.smoke = smoke;
    }

    public void sell(){
        //System.out.println("香烟"+this.name+"这次卖的价格是"+this.price+"元");
        System.out.println("我是代理人我要加价");
        smoke.setPrice(smoke.getPrice()+1);
        smoke.sell();
    }


}
