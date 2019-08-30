package write;

import java.io.File;

/**
 * FileName: PDFWriter
 * Author: yeyang
 * Date: 2018/6/19 11:51
 */
public class PDFWriter extends FileWriter{
    @Override
    public void write() {
        System.out.println(database.read());
        System.out.println("输出为PDF格式");
    }
}
