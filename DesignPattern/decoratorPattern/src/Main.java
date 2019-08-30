import bantouming.Approver;
import bantouming.Deleter;
import bantouming.Document;
import bantouming.Purchase;
import encrptExample.Encrpt;
import encrptExample.MoveDecorator;
import encrptExample.ReverseDecorator;
import encrptExample.StringBean;
import touming.Hero;
import touming.Skill;
import touming.SkillQ;
import touming.SkillW;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Document document = new Purchase();
        Approver approverDeco = new Approver(document);
        approverDeco.approver();
        Document deleDeco = new Deleter(document);
        deleDeco.display();

        System.out.println("---------------------");
        Skill hero = new Hero();
        Skill q = new SkillQ(hero);
        Skill w = new SkillW(q);
        w.display();

        System.out.println("---------------------");
        Encrpt obj = new StringBean();
        obj.setS("李四");
        Encrpt e1,e2,e3;
        e1 = new MoveDecorator(obj);
        e2 = new MoveDecorator(e1);
        e3 = new ReverseDecorator(e2);
        e3.encrpt(obj);
        System.out.println(obj.getS());

    }
}
