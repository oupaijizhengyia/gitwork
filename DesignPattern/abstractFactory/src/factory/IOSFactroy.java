package factory;

import interfaceController.IOSInterface;
import interfaceController.InterFaceController;
import operateController.IOSOperate;
import operateController.OperateController;

/**
 * FileName: IOSFactroy
 * Author: yeyang
 * Date: 2018/6/4 10:36
 */
public class IOSFactroy extends SystemFactory {
    @Override
    public OperateController createOperate() {
        return new IOSOperate();
    }

    @Override
    public InterFaceController createInterface() {
        return new IOSInterface();
    }

}
