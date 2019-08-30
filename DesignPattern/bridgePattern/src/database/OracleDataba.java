package database;

/**
 * FileName: OracleDataba
 * Author: yeyang
 * Date: 2018/6/19 11:45
 */
public class OracleDataba implements Database {
    @Override
    public String read() {
        return "从Oracle中读取到的数据";
    }
}
