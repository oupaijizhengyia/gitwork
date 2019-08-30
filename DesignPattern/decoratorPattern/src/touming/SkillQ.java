package touming;

/**
 * FileName: SkillQ
 * Author: yeyang
 * Date: 2018/7/2 10:26
 */
public class SkillQ extends Study {
    public SkillQ(Skill skill) {
        super(skill);
    }
    @Override
    public void display(){
        this.setQ();
        super.display();
    }
    public void setQ(){
        System.out.println("A hero study Q");
    }
}
