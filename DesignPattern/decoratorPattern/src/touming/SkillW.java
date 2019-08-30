package touming;

/**
 * FileName: SkillW
 * Author: yeyang
 * Date: 2018/7/2 10:44
 */
public class SkillW extends Study {
    public SkillW(Skill skill) {
        super(skill);
    }
    public void setW(){
        System.out.println("A hero study W");
    }
    @Override
    public void display(){
        this.setW();
        super.display();
    }
}
