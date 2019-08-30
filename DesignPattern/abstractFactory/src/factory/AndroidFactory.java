package factory;

import interfaceController.AndroidInterface;
import interfaceController.InterFaceController;
import operateController.AndroidOperate;
import operateController.OperateController;

/**
 * FileName: AndroidFactory
 * Author: yeyang
 * Date: 2018/6/4 10:35
 */
public class AndroidFactory extends SystemFactory {
    @Override
    public OperateController createOperate() {
        return new AndroidOperate();
    }

    @Override
    public InterFaceController createInterface() {
        return new AndroidInterface();
    }

}
