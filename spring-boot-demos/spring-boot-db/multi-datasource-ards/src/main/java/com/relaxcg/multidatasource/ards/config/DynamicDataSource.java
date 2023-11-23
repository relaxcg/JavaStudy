package com.relaxcg.multidatasource.ards.config;

import com.relaxcg.multidatasource.ards.base.DataSourceContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author relaxcg
 * @date 2023/11/22 14:06
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContext.get();
    }
}
