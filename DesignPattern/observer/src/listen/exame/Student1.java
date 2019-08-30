package listen.exame;

/**
 * FileName: Student
 * Author: yeyang
 * Date: 2019/8/14 9:45
 * 自己心算
 */
public class Student1 {

    private Integer caculator(int i,int j){
        return i + j;
    }
    public void fill(int i, int j ){
        System.out.println(i+" + "+j+" = "+caculator(i,j));
    }
}
