package database;

/**
 * FileName: SqlServerDatabase
 * Author: yeyang
 * Date: 2018/6/19 11:46
 */
public class SqlServerDatabase implements Database {
    @Override
    public String read() {
        return "从SqlServer中读取到的数据";
    }
}
