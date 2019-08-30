package builder;

import active.ControlLine;
import active.SaveList;
import active.Window;

/**
 * FileName: SavePattern
 * Author: yeyang
 * Date: 2018/6/14 10:04
 */
public class SavePatternBuilder extends PatternBuilder {
    @Override
    void builderMenu() {

    }


    @Override
    void builderStartList() {

    }


    @Override
    void builderSaveList() {
        SaveList saveList = new SaveList();
        super.windowPattern.setSaveList(saveList);
    }
}
