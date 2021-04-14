package com.cxy.performancecounter;

import com.cxy.performancecounter.v2.api.MetricsCollector;
import com.cxy.performancecounter.v2.api.RequestInfo;
import com.cxy.performancecounter.v2.metricsStorage.MetricsStorage;
import com.cxy.performancecounter.v2.metricsStorage.RedisMetricsStorage;
import com.cxy.performancecounter.v2.reporters.ConsoleReporter;
import com.cxy.performancecounter.v2.stat.Aggregator;
import com.cxy.performancecounter.v2.stat.RequestStat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Map;

/**
 * Description:   </br>
 * Date: 2021/4/10 18:28
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCounterV2 {


    /**
     * 测试 MetricsCollector 组件的 recordRequest方法
     */
    @Test
    public void testMetricsCollector() throws InterruptedException {
        MetricsCollector collector = new MetricsCollector();
        collector.recordRequest(new RequestInfo("register", 123, System.currentTimeMillis()));

        collector.recordRequest(new RequestInfo("register", 223, System.currentTimeMillis()+3000));

        collector.recordRequest(new RequestInfo("register", 323, System.currentTimeMillis()+6000));

        collector.recordRequest(new RequestInfo("login", 23, System.currentTimeMillis()+9000));

        collector.recordRequest(new RequestInfo("login", 1223, System.currentTimeMillis()+12000));
    }


    @Test
    public void testMetricsStorage() throws JsonProcessingException {
        MetricsStorage storage = new RedisMetricsStorage();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<RequestInfo>> map =  storage.getRequestInfos(1618369497105L,1618369503142L);

        List<RequestInfo> registerDataList =  storage.getRequestInfos("register",1618369497105L,1618369503142L);


        System.out.println(mapper.writeValueAsString(map));


        System.out.println(mapper.writeValueAsString(registerDataList));

    }

    @Test
    public void testAggregator() {
        Aggregator aggregator = new Aggregator();
        MetricsStorage storage = new RedisMetricsStorage();

        Map<String, List<RequestInfo>> requestInfos =
                storage.getRequestInfos(1618386950033L, 1618386956088L);

        Map<String, RequestStat> segmentStat = aggregator.aggregate(
                requestInfos);

        System.out.println();
    }

    @Test
    public void testv2() throws InterruptedException {



        MetricsCollector collector = new MetricsCollector();
        collector.recordRequest(new RequestInfo("test", 100, System.currentTimeMillis()));


        collector.recordRequest(new RequestInfo("test", 200, System.currentTimeMillis()+1000));


        collector.recordRequest(new RequestInfo("test", 300, System.currentTimeMillis()+2000));


        collector.recordRequest(new RequestInfo("test", 100, System.currentTimeMillis()+3000));


        collector.recordRequest(new RequestInfo("test", 200, System.currentTimeMillis()+4000));



        ConsoleReporter consoleReporter = new ConsoleReporter();
        //间隔1s，打印上60s的聚合统计结果
        consoleReporter.startRepeatedReport(1, 60);

//        List<String> emailToAddresses = new ArrayList<>();
//        emailToAddresses.add("wangzheng@xzg.com");
//        EmailReporter emailReporter = new EmailReporter(emailToAddresses);
//        emailReporter.startDailyReport();

        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




}