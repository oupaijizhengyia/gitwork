package write;

/**
 * FileName: XmlWriter
 * Author: yeyang
 * Date: 2018/6/19 11:52
 */
public class XmlWriter extends FileWriter {
    @Override
    public void write() {
        System.out.println(database.read());
        System.out.println("输出为xml格式");
    }
}
