package com.cxy.demo.dynamicallyswitch;


import com.cxy.demo.dynamicallyswitch.change.DataSourcesHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源类
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Autowired
    private DataSourcesHolder dataSourcesHolder;

    @Override
    protected Object determineCurrentLookupKey() {
       return dataSourcesHolder.getSources();
    }
}
