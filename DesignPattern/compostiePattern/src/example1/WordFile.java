package example1;

/**
 * FileName: WordFile
 * Author: yeyang
 * Date: 2018/6/22 14:49
 */
public class WordFile extends AbstractFile {
    private String name;
    public WordFile(String name){
        this.name = name;
    }
    @Override
    public void killVirus() {
        System.out.println("word类型的"+name+"正在杀毒");
    }
}
