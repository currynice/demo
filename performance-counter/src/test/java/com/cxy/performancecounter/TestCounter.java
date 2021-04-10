package com.cxy.performancecounter;

import com.cxy.performancecounter.v1.api.MetricsCollector;
import com.cxy.performancecounter.v1.api.RequestInfo;
import com.cxy.performancecounter.v1.metricsStorage.MetricsStorage;
import com.cxy.performancecounter.v1.metricsStorage.RedisMetricsStorage;
import com.cxy.performancecounter.v1.reporters.ConsoleReporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:   </br>
 * Date: 2021/4/10 18:28
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCounter {

    @Test
    public void contextLoads() {

        MetricsStorage storage = new RedisMetricsStorage();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        //间隔60s，打印上60s的聚合统计结果
        consoleReporter.startRepeatedReport(6, 6);

//        EmailReporter emailReporter = new EmailReporter(storage);
//        emailReporter.addToAddress("wangzheng@xzg.com");
//        emailReporter.startDailyReport();


        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, System.currentTimeMillis()));
        collector.recordRequest(new RequestInfo("register", 223, System.currentTimeMillis()+10));
        collector.recordRequest(new RequestInfo("register", 323, System.currentTimeMillis()+20));
        collector.recordRequest(new RequestInfo("login", 23, System.currentTimeMillis()+30));
        collector.recordRequest(new RequestInfo("login", 1223, System.currentTimeMillis()+40));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}