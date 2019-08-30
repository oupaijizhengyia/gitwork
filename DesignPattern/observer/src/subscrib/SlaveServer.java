package subscrib;

/**
 * FileName: SlaveServer
 * Author: yeyang
 * Date: 2019/8/13 14:14
 */
public class SlaveServer implements Server {
    private String name ;

    @Override
    public void subScribe(Server master) {
        master.subScribe(this);
    }

    @Override
    public void quit(Server server) {
        server.quit(this);
    }

    @Override
    public void pushMsg(String msg) {

    }

    @Override
    public void receiveMsg(String msg) {
        System.out.println("我是"+name+", 我收到了条消息,内容为"+msg);
    }

    @Override
    public String getServerName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
