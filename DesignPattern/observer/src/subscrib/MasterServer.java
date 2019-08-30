package subscrib;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: MasterServer
 * Author: yeyang
 * Date: 2019/8/13 14:05
 */
public class MasterServer implements Server {
    private List<Server> list = new ArrayList();
    @Override
    public void subScribe(Server server) {
        list.add(server);
        System.out.println(server.getServerName()+"订阅成功");
    }


    @Override
    public void quit(Server server) {
        list.remove(server);
    }

    @Override
    public void pushMsg(String msg) {
        list.forEach(o->{
            o.receiveMsg(msg);
        });
    }

    @Override
    public void receiveMsg(String msg) {

    }

    @Override
    public String getServerName() {
        return null;
    }
}
