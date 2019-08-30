package builder;

import active.ControlLine;
import active.Menu;
import active.StartList;
import active.Window;



/**
 * FileName: PatternBuilder
 * Author: yeyang
 * Date: 2018/6/14 9:28
 */
public abstract class PatternBuilder {
    protected WindowPattern windowPattern = new WindowPattern();
    abstract void builderMenu();

    abstract void builderStartList();

    abstract void builderSaveList();
    public void builderWindow(){
        Window window = new Window();
        windowPattern.setWindow(window);
    };
    public void builderControlLine(){
        ControlLine controlLine = new ControlLine();
        windowPattern.setControlLine(controlLine);
    };
    public WindowPattern createWindowPattern(){

        return windowPattern;
    }
}
