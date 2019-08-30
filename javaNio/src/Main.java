import com.sun.nio.file.ExtendedOpenOption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {

    public static void main(String[] args) throws IOException {
        FileChannel inf =FileChannel.open(Paths.get("s.txt"),StandardOpenOption.READ);
        FileChannel outf =FileChannel.open(Paths.get("sdps7s9.txt"),StandardOpenOption.WRITE,
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE
        );
        outf.transferFrom(inf,0,inf.size());
        System.out.println("操作结束");
    }
}
