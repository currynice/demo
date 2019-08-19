package com.cxy.demo.dynamicallyswitch.change;


import com.cxy.demo.dynamicallyswitch.DataSources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 *切换
 */
@Service
@Slf4j(topic = "Logger")
public class DataSourcesHolder {
    private static final String PRIMARY_DATASOURCE = DataSources.MASTER_DB;
    private static ThreadLocal<String> sources = new ThreadLocal<>();
    //失败了，还原默认数据源
    public void initWhenError(){
        sources.set(PRIMARY_DATASOURCE);
    }

    //获取
    public String getSources(){
        return sources.get();
    }

    //设置
    public  void setSources(String source) {
        log.info("正在切换数据源{}...",source);
        sources.set(source);
    }

    //清除
    public void clear(){
        sources.remove();
    }
}
