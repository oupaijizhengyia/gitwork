package builder;

import active.Menu;
import active.StartList;

/**
 * FileName: TotalPatternBuilder
 * Author: yeyang
 * Date: 2018/6/14 9:36
 */
public class TotalPatternBuilder extends PatternBuilder{

    @Override
    void builderMenu() {
        Menu menu = new Menu();
        super.windowPattern.setMenu(menu);
    }


    @Override
    void builderStartList() {
        StartList startList = new StartList();
        super.windowPattern.setStartList(startList);
    }

    @Override
    void builderSaveList() {

    }
}
