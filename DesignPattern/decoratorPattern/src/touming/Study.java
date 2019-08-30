package touming;

/**
 * FileName: Study
 * Author: yeyang
 * Date: 2018/7/2 10:24
 */
public class Study extends Skill {
    private Skill skill;
    public Study(Skill skill){
        this.skill = skill;
    }
    @Override
    public void display() {
        skill.display();
    }
}
