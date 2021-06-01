package com.cxy.performancecounter.v2.reporters;


import com.cxy.performancecounter.v2.metricsStorage.MetricsStorage;
import com.cxy.performancecounter.v2.metricsStorage.RedisMetricsStorage;
import com.cxy.performancecounter.v2.viewer.ConsoleViewer;
import com.cxy.performancecounter.v2.viewer.StatViewer;
import com.cxy.performancecounter.v2.stat.Aggregator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Description: 以一定频率统计并发送统计数据到命令行和邮件。</br>
 *
 *  至于 ConsoleReporter 和 EmailReporter 是否可以抽象出可复用的抽象类，或者抽象出一个公共的接口，我们暂时还不能确定。
 *
 *  1:根据给定的时间区间，从数据源拉取数据；
 *  2:调用Aggregator（使用原始数据），得到计算后的统计数据；
 *  3:将统计数据显示到命令行；
 *  4:定时触发以上 3 个过程的执行。
 *
 *
 *    1)ConsoleReporter 和 EmailReporter 类中存在重复代码（从数据源中取数据、做统计的聚合）。
 *
 *    2)显示部分的代码有的简单，有的复杂（Email方式），v2将显示(数据通知)剥离出来，设计成一个独立的类。
 *
 *    3) 代码中涉及线程操作，并且调用 Aggregator 的静态函数，代码的可测试性也有待提高。
 *
 * Date: 2021/4/10 12:31
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class ConsoleReporter extends ScheduledReporter{

    //todo   startRepeatedReport 被调用多次，不仅ScheduledExecutorService会排队执行，造成资源浪费
    //且控制台打印重复日志
    // 内部维护一个可视字段 started。然后在方法执行时，优先判断该字段是否已经变为true。如果是则不再往下执行。也算是保证该函数的幂等性。
    private  AtomicBoolean started = new AtomicBoolean(false);

    private ScheduledExecutorService executor;


    // 为了代码易用性，新增一个封装了默认依赖(Redis存储，Console输出)的构造函数
    public ConsoleReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new ConsoleViewer());
    }


    // 兼顾灵活性和代码的可测试性，这个基础的构造函数继续保留
    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }




    /**
     * 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
     * 间隔 periodInSeconds 秒 ，输出上 durationInSeconds 秒的数据
     * @param periodInSeconds
     * @param durationInSeconds
     */
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(!started()){
                    started.set(true);
                    System.out.println("开始执行console reporter");
                    // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                    long durationInMillis = durationInSeconds * 1000;
                    long endTimeInMillis = System.currentTimeMillis();
                    long startTimeInMillis = endTimeInMillis - durationInMillis;
                    doStatAndReport(startTimeInMillis,endTimeInMillis);
                    started.set(false);
                }
            }
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }

    private boolean started(){
        return started.get();
    }

}


