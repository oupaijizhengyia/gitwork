package example1;

/**
 * FileName: PictureFile
 * Author: yeyang
 * Date: 2018/6/22 14:49
 */
public class PictureFile extends AbstractFile {
    private String name;
    public PictureFile(String name){
        this.name = name;
    }
    @Override
    public void killVirus() {
        System.out.println("照片类型的"+name+"正在杀毒");
    }
}
