package write;

import database.Database;

/**
 * FileName: FileWrite
 * Author: yeyang
 * Date: 2018/6/19 11:48
 */
public abstract class FileWriter {
    protected Database database;

    public void setDatabase(Database database){
        this.database = database;
    }
    public abstract void write();
}
