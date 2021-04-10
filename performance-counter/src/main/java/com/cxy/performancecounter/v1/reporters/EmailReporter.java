//package com.cxy.performancecounter.v1.reporters;
//
//import com.cxy.performancecounter.v1.api.RequestInfo;
//import com.cxy.performancecounter.v1.metricsStorage.MetricsStorage;
//import com.cxy.performancecounter.v1.stat.Aggregator;
//import com.cxy.performancecounter.v1.stat.RequestStat;
//
//import java.util.*;
//
///**
// * Description:
// *
// *    1:根据给定的时间区间，从数据源拉取数据；
// *    2:调用Aggregator（使用原始数据），得到计算后的统计数据；
// *    3:将统计数据发送到Email；
// *    4:定时触发以上 3 个过程的执行。</br>
// * Date: 2021/4/10 12:31
// *
// * @author :cxy </br>
// * @version : 1.0 </br>
// */
//public class EmailReporter {
//
//
//    private static final Long DAY_HOURS_IN_SECONDS = 86400L;
//
//    private MetricsStorage metricsStorage;
//    private EmailSender emailSender;
//    private List<String> toAddresses = new ArrayList<>();
//
//    public EmailReporter(MetricsStorage metricsStorage) {
//        this(metricsStorage, new EmailSender(/*省略参数*/));
//    }
//
//    public EmailReporter(MetricsStorage metricsStorage, EmailSender emailSender) {
//        this.metricsStorage = metricsStorage;
//        this.emailSender = emailSender;
//    }
//
//    public void addToAddress(String address) {
//        toAddresses.add(address);
//    }
//
//    public void startDailyReport() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date firstTime = calendar.getTime();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
//                long endTimeInMillis = System.currentTimeMillis();
//                long startTimeInMillis = endTimeInMillis - durationInMillis;
//                Map<String, List<RequestInfo>> requestInfos =
//                        metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
//                Map<String, RequestStat> stats = new HashMap<>();
//                for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
//                    String apiName = entry.getKey();
//                    List<RequestInfo> requestInfosPerApi = entry.getValue();
//                    RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis);
//                    stats.put(apiName, requestStat);
//                }
//                // TODO: 格式化为html格式发送邮件
//            }
//        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
//    }
//}
