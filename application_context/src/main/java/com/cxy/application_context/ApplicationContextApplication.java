package com.cxy.application_context;

import com.cxy.application_context.benas.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Slf4j
public class ApplicationContextApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationContextApplication.class, args);
    }

    /**
     * 	子WebApplicationContext中定义的拦截通过父WebApplicationContext是获取不到的，所以不会生效的.
     * 	对底层的专属配置定义在底层的上下文，对上层的专属配置定义在上层的上下文，
     * 	通用配置定义在底层的上下文(Root WebApplicationContext)
      * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //FooConfig中定义的bean
        ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);

        // applicationContext.xml文件中定义的bean
        ClassPathXmlApplicationContext barContext = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"}, fooContext);
        TestBean bean = fooContext.getBean("testBeanX", TestBean.class);
        bean.hello();

        log.info("=============");


        bean = barContext.getBean("testBeanX", TestBean.class);
        bean.hello();

        bean = barContext.getBean("testBeanY", TestBean.class);
        bean.hello();
    }
}
