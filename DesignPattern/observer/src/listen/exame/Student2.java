package listen.exame;

/**
 * FileName: Student2
 * Author: yeyang
 * Date: 2019/8/14 9:58
 * 悄悄的求助别人算
 */
public class Student2 {

    public void fill(int i, int j ){
        int call = callGirl(i,j);
        System.out.println(i+" + "+j+" = "+call);
    }

    private int callGirl(int i, int j) {
        Gril2 gril2 = new Gril2();
        return gril2.caculator(i,j);
    }
}
