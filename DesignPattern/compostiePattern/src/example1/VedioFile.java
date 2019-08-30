package example1;

/**
 * FileName: VedioFile
 * Author: yeyang
 * Date: 2018/6/22 14:43
 */
public class VedioFile extends AbstractFile {
    private String name;
    public VedioFile(String name){
        this.name = name;
    }
    @Override
    public void killVirus() {
        System.out.println("视频类型的"+name+"正在杀毒");
    }
}
