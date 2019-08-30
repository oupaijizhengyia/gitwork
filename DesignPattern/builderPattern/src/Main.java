import active.SaveList;
import builder.PatternBuilder;
import builder.PatternDerector;
import builder.WindowPattern;
import util.DataUtil;

public class Main {

    public static void main(String[] args) {
        PatternBuilder patternBuilder = (PatternBuilder) DataUtil.getBean();
        PatternDerector patternDerector = new PatternDerector();
        WindowPattern windowPattern = patternDerector.create(patternBuilder);
       windowPattern.display();
    }
}
