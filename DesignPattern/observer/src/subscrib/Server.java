package subscrib;

/**
 * FileName: Server
 * Author: yeyang
 * Date: 2019/8/13 14:03
 */
public interface Server {
    void subScribe(Server server);
    void quit(Server server);
    void pushMsg(String msg);
    void receiveMsg(String msg);
    String getServerName();
}
