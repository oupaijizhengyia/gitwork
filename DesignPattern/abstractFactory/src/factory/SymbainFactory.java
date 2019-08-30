package factory;

import interfaceController.InterFaceController;
import interfaceController.SymbainInterface;
import operateController.OperateController;
import operateController.SymbianOperate;

/**
 * FileName: SymbainFactory
 * Author: yeyang
 * Date: 2018/6/4 10:36
 */
public class SymbainFactory extends SystemFactory {
    @Override
    public OperateController createOperate() {
        return new SymbianOperate();
    }

    @Override
    public InterFaceController createInterface() {
        return new SymbainInterface();
    }
}
