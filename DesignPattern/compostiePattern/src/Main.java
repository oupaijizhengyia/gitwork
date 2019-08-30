import example1.*;

public class Main {

    public static void main(String[] args) {
        AbstractFile file1,file2,file3,file4,file5,folder1,folder2,folder3,folder4;

        folder1 = new Folder("Sunny的资料");
        folder2 = new Folder("图像文件");
        folder3 = new Folder("文本文件");
        folder4 = new Folder("视频文件");
        file1 = new PictureFile("小龙女.jpg");
        file2 = new PictureFile("张无忌.gif");
        file3 = new WordFile("九阴真经.doc");
        file4 = new WordFile("葵花宝典.doc");
        file5 = new VedioFile("笑傲江湖.rmvb");

        folder1.add(folder2);
        folder1.add(folder3);
        folder1.add(folder4);
        folder2.add(file1);
        folder2.add(file2);
        folder3.add(file3);
        folder3.add(file4);
        folder4.add(file5);

        folder3.getChile(1).killVirus();

    }
}
