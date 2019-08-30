package com.tangu.tangucore.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**d
 * @author fenglei
 * @date 10/27/17
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceTenantLocal.getDataSourceName();
    }
}
