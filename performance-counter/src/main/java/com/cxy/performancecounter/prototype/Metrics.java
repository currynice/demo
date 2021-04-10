package com.cxy.performancecounter.prototype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * 最终目标:
 * 首先要采集每次接口请求的响应时间,时间戳等信息
 * 将采集的数据存起来
 * 然后按照某个时间间隔做聚合统计，
 * 最后将结果格式化输出。
 *
 * 数据采集：负责打点采集原始数据，包括记录每次接口请求的响应时间和请求时间。数据采集要高度容错，且不能影响到接口本身的可用性。
 *       此外数据采集 API 需要保证易用性。
 *
 * 数据存储：保存采集的原始数据，方便后面做聚合统计。可以存在 Redis、MySQL、HBase、日志、文件、内存中。
 *          为了减少这一步对接口性能的影响，采集和存储需要异步完成。
 *
 * 聚合统计：处理原始数据，聚合为统计数据：max、min、avg、pencentile、count、tps 等。
 *          为了支持更多的聚合统计规则，代码要灵活、可扩展。
 *
 * 显示：负责将统计数据以某种格式显示，如：主动输出到命令行、邮件； 被动响应到网页； 自定义显示终端等。
 *
 *   </br>
 * Date: 2021/4/10 11:12
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class Metrics {
    // Map的key是接口名称，value对应接口请求的响应时间
    private Map<String, List<Double>> responseTimes = new HashMap<>();

    // Map的key是接口名称，value对应接口请求的时间戳；
    private Map<String, List<Double>> timestamps = new HashMap<>();

    //定时执行器
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    //记录接口请求的响应时间。
    public void recordResponseTime(String apiName, double responseTime) {
        responseTimes.putIfAbsent(apiName, new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    // 记录接口请求的访问时间戳
    public void recordTimestamp(String apiName, double timestamp) {
        timestamps.putIfAbsent(apiName, new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    /**
     * 以指定的频率统计数据并输出格式化结果到控制台。
     * @param period
     * @param unit
     */
    public void startRepeatedReport(long period, TimeUnit unit){
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ObjectMapper mapper = new ObjectMapper();
                //计算的统计结果
                Map<String, Map<String, Double>> stats = new HashMap<>();
                for (Map.Entry<String, List<Double>> entry : responseTimes.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiRespTimes = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("max", max(apiRespTimes));
                    stats.get(apiName).put("min", min(apiRespTimes));
                    stats.get(apiName).put("avg", avg(apiRespTimes));
                }

                for (Map.Entry<String, List<Double>> entry : timestamps.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiTimestamps = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("count", (double)apiTimestamps.size());
                }
                try {
                    //输出到控制台
                    System.out.println(mapper.writeValueAsString(stats));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }, 0, period, unit);
    }

    private double max(List<Double> dataset) {
      return dataset.stream().max(Double::compare).get();
    }

    private double min(List<Double> dataset) {//省略代码实现
        return dataset.stream().min(Double::compare).get();
    }

    private double avg(List<Double> dataset) {
      return dataset.stream().mapToDouble(d->d).average().getAsDouble();
    }


//    public static void main(String[] args) {
//        List<Double> datalist = Arrays.asList(1.0,2.0,3.0);
//        Metrics metrics = new Metrics();
//        System.out.println(metrics.max(datalist));
//
//        System.out.println(metrics.min(datalist));
//
//        System.out.println(metrics.avg(datalist));
//    }
}


