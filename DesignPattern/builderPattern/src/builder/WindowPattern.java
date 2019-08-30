package builder;

import active.*;

/**
 * FileName: Pattern
 * Author: yeyang
 * Date: 2018/6/14 9:31
 */
public class WindowPattern {
    public WindowPattern(){};
    private ControlLine controlLine;
    private Menu menu;
    private StartList startList;
    private Window window;
    private SaveList saveList;

    public SaveList getSaveList() {
        return saveList;
    }

    public void setSaveList(SaveList saveList) {
        this.saveList = saveList;
    }

    public ControlLine getControlLine() {
        return controlLine;
    }

    public void setControlLine(ControlLine controlLine) {
        this.controlLine = controlLine;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public StartList getStartList() {
        return startList;
    }

    public void setStartList(StartList startList) {
        this.startList = startList;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void display(){
        if(controlLine != null){
            controlLine.display();
        } if(menu != null){
            menu.display();
        } if(startList != null){
            startList.display();
        } if(saveList != null){
            saveList.display();
        } if(window != null){
            window.display();
        }
    }
}
