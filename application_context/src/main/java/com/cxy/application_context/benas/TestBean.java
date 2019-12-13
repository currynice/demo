package com.cxy.application_context.benas;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试 Bean,根据name不同进行不同的处理策略
 *     <bean id="testBeanX" class="com.cxy.application_context.benas.TestBean">
 *         <constructor-arg name="info" value="?" />
 *     </bean>
 */
@AllArgsConstructor
@Slf4j
public class TestBean {
    private String info;

    public void hello() {
        log.info("hello " + info);
    }
}
