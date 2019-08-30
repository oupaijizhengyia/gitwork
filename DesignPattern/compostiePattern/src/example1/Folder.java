package example1;

/**
 * FileName: FolderFile
 * Author: yeyang
 * Date: 2018/6/22 14:27
 */
public class Folder extends AbstractFile {
    private String name;
    public Folder(String name){
        this.name = name;
    }
    @Override
    public void add(AbstractFile file) {
        fileList.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    @Override
    public AbstractFile getChile(int i) {
        return fileList.get(i);
    }

    @Override
    public void killVirus() {
        System.out.println("文件夹类型的"+name+"正在杀毒");
        fileList.forEach(o->{
            o.killVirus();
        });
    }
}
