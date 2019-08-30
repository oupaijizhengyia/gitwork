package write;

/**
 * FileName: TxtWrite
 * Author: yeyang
 * Date: 2018/6/19 11:50
 */
public class TxtWriter extends FileWriter {
    @Override
    public void write() {
        System.out.println(database.read());
        System.out.println("输出为txt格式");
    }
}
