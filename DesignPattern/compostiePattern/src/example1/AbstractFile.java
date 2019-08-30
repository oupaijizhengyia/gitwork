package example1;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: AbstractFile
 * Author: yeyang
 * Date: 2018/6/22 14:18
 */
public abstract class AbstractFile {
    protected List<AbstractFile> fileList = new ArrayList<>(16);
     public void add(AbstractFile file){
         throw new UnsupportedOperationException("不支持此方法");
     }
     public void remove(AbstractFile file){
         throw new UnsupportedOperationException("不支持此方法");
     }
     public   AbstractFile getChile(int i){
         throw new UnsupportedOperationException("不支持此方法");
     };
     public abstract void killVirus();
}
