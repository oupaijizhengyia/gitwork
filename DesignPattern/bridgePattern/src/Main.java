import database.Database;
import util.DataUtil;
import write.FileWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        FileWriter fileWriter = (FileWriter)DataUtil.getBean("writer");
        Database database = (Database)DataUtil.getBean("database");
        fileWriter.setDatabase(database);
        fileWriter.write();
    }
}
