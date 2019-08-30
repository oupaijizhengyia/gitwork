import java.util.HashMap;
import java.util.Map;

/**
 * FileName: Factory
 * Author: yeyang
 * Date: 2018/7/10 10:27
 */
public class Factory {
    private static Factory instance = null;
    private Factory(){};
    private Map<String,Example> poor = new HashMap(16);
    private Example example = null;
    public Example getExample(String str){
        if("Pic".equals(str)){
            if(poor.get(str) == null){
                example = new Pic();
                poor.put(str,example);
            }else {
                return poor.get(str);
            }
        }else if("Gif".equals(str)){
            if(poor.get(str) == null){
                example = new Gif();
                poor.put(str,example);
            }else {
                return poor.get(str);
            }
        }
        return example;
    }
    public static Factory getFactory(){
        if(instance == null){
            instance = new Factory();
            return instance;
        }
        return instance;
    }
}
