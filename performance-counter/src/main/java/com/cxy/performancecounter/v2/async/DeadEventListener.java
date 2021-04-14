package com.cxy.performancecounter.v2.async;


import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * Description: 对于找不到处理方法的事件（可能某些监听类的@SubScribe缺失或监听类加载失败）</br>
 *               EventBus会将这些事件包装成 DeadEvent  </br>
 *               因此本类就是专门处理 DeadEvent 的
 * Date: 2021/4/13 23:15
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class DeadEventListener {

    /**
     * DeadEvent 事件监听方法
     * @param event
     */
    @Subscribe
    public void logDeadEvent(final DeadEvent event) {
        System.out.println("do logDeadEvent....");
        if(null==event || null==event.getSource()||null==event.getEvent()){
            return;
        }
        System.out.println(event.toString());
    }
}
