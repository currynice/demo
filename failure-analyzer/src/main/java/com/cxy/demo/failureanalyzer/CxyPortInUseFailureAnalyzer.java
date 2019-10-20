package com.cxy.demo.failureanalyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.web.server.PortInUseException;

/**
 * @Author: cxy
 * @Date: 2019/10/20 10:33
 * {@link org.springframework.boot.diagnostics.analyzer.PortInUseFailureAnalyzer}
 * @Description: 仿写默认的端口异常分析器,并注册(META-INF/spring.factories文件)
 */
public class CxyPortInUseFailureAnalyzer extends AbstractFailureAnalyzer<PortInUseException> {
    static Logger logger = LoggerFactory.getLogger(CxyPortInUseFailureAnalyzer.class);

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, PortInUseException cause) {
        logger.error("端口被占用。", cause);
        return new FailureAnalysis("端口号：" + cause.getPort() + "被占用", "更改端口号", rootFailure);
    }
}
