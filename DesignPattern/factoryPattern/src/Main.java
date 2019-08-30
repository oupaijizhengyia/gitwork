import com.demo.FileUtil;
import com.demo.PhotoReadFactory;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        PhotoReadFactory factory = (PhotoReadFactory)FileUtil.getBean();
        factory.readPhoto();

    }
}
