import subscrib.MasterServer;
import subscrib.SlaveServer;

public class Main {

    public static void main(String[] args) {
        MasterServer master = new MasterServer();

        SlaveServer server1 = new SlaveServer();
        server1.setName("从服务器 1");
        server1.subScribe(master);

        SlaveServer server2 = new SlaveServer();
        server2.setName("从服务器 2");
        server2.subScribe(master);

        SlaveServer server3 = new SlaveServer();
        server3.setName("从服务器 3");
        server3.subScribe(master);

        SlaveServer server4 = new SlaveServer();
        server4.setName("从服务器 4");
        server4.subScribe(master);


        SlaveServer server5 = new SlaveServer();
        server5.setName("从服务器 5");
        server5.subScribe(master);

        master.pushMsg("现在开始更新用户表");

        server3.quit(master);
        System.out.println("----------");
        master.pushMsg(" 后退, 我要开始了");

        /*
            事件监听器模型
            事件 -> 观察目标 -> 主服务器
            监听器 -> 观察者 -> 从服务器

            不同的是这个模型是一对一的  而且中间可能会有中间通知对象
         */
    }
}
