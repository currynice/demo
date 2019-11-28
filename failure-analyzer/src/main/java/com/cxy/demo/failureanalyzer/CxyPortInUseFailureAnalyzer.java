package com.cxy.demo.failureanalyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.boot.web.server.PortInUseException;

/**
 * @Author: cxy
 * @Date: 2019/10/20 10:33
 *
 * @Description:
 * 启动时打印具体的堆栈错误信息,而不是简单的错误消息
 * {@link FailureAnalyzer#analyze(Throwable)} 该方法将异常对象failure 交给实现类处理
 * {@link AbstractFailureAnalyzer}是FailureAnalyzer的基础实现类,提供了<<⼀个指定异常类型>>⼀个指定异常类型>的抽象⽅法 analyze
 * 1.根据getCauseType()⽅法获取当前类定义的第⼀个泛型类型，也就是需要分析的指定	异常类型
 * 2.获取泛型异常类型后,findCause()判断	Throwable是否与泛型异常类型匹配，如果匹配直接返回给SpringBoot进⾏注册处理。
 *
 *
 * 仿写默认的端口异常分析器,并注册(META-INF/spring.factories文件)
 * {@link org.springframework.boot.diagnostics.analyzer.PortInUseFailureAnalyzer}
 *
 */
public class CxyPortInUseFailureAnalyzer extends AbstractFailureAnalyzer<PortInUseException> {
    static Logger logger = LoggerFactory.getLogger(CxyPortInUseFailureAnalyzer.class);

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, PortInUseException cause) {
        logger.error("端口被占用。", cause);
        return new FailureAnalysis("端口号：" + cause.getPort() + "被占用", "更改端口号", rootFailure);
    }
}
