package database;

import java.io.FileOutputStream;

/**
 * FileName: MysqlDatabase
 * Author: yeyang
 * Date: 2018/6/19 11:45
 */
public class MysqlDatabase implements Database{
    @Override
    public String read() {
        return "从mysql中读取到的数据";
    }
}
