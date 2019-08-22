package com.cxy.demo.loading.config;

import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 管理内嵌的tomcat容器
 */
@Component
public class TomcatConfig{
//        implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{
//
//    @Override
//    public void customize(ConfigurableServletWebServerFactory serverFactory) {
//        serverFactory.setPort(8088);
//        serverFactory.setContextPath("/loading");
//
//        ((TomcatServletWebServerFactory)serverFactory).addConnectorCustomizers( connector->{
//            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//            protocol.setMaxConnections(200);
//            protocol.setMaxThreads(200);
//            protocol.setSelectorTimeout(3000);
//            protocol.setSessionTimeout(3000);
//            protocol.setConnectionTimeout(3000);
//        });
//    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(9000);
       factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
       return factory;
    }
}
