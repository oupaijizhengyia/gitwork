package factory;

import interfaceController.InterFaceController;
import operateController.OperateController;

/**
 * FileName: factory
 * Author: yeyang
 * Date: 2018/6/4 10:32
 */
public abstract class SystemFactory {
   abstract OperateController createOperate();
   abstract InterFaceController createInterface();
   public void operate(){
       OperateController operateController =  this.createOperate();
       InterFaceController interFaceController = this.createInterface();
       operateController.display();
       interFaceController.say();
   };
}
