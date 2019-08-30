package builder;

/**
 * FileName: PatternDerector
 * Author: yeyang
 * Date: 2018/6/14 10:14
 */
public class PatternDerector{
    public WindowPattern create(PatternBuilder patternBuilder){
        patternBuilder.builderControlLine();
        patternBuilder.builderMenu();
        patternBuilder.builderSaveList();
        patternBuilder.builderWindow();
        patternBuilder.builderStartList();
        WindowPattern windowPattern = patternBuilder.windowPattern;
        return windowPattern;
    }
}
