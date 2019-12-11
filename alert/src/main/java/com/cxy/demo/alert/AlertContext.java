package com.cxy.demo.alert;

import com.cxy.demo.alert.handlers.ErrorAlertHandler;
import com.cxy.demo.alert.handlers.TimeOutTpsAlertHandler;
import com.cxy.demo.alert.handlers.TpsAlertHandler;

public class AlertContext {

        private AlertRule alertRule;
        private Notification notification;
        private Alert alert;

    /**
     * 负责 Alert 的创建、组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作。
     */
    public void initializeBeans() {
            alertRule = new AlertRule();
            notification = new Notification();
            alert = new Alert();
            alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
            alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
            alert.addAlertHandler(new TimeOutTpsAlertHandler(alertRule,notification));
        }
        public Alert getAlert() { return alert; }

        // 饿汉式单例
        private static final AlertContext instance = new AlertContext();
        private AlertContext() {
            instance.initializeBeans();
        }
        public static AlertContext getInstance() {
            return instance;
        }
    }

    public class Demo {
        public static void main(String[] args) {
            ApiStatInfo apiStatInfo = new ApiStatInfo();
            // set...
            // 校验apiStatInfo各个属性不为null
            AlertContext.getInstance().getAlert().check(apiStatInfo);
        }
    }
