package uz.sardorbroo.multitenancy.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import uz.sardorbroo.multitenancy.config.properties.TenantProperties;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DynamicDatasource extends AbstractRoutingDataSource {

    public DynamicDatasource(DataSourceProperties commonDsProperties,
                             TenantProperties tenantDsProperties) {

        DataSource commonDS = initCommonDataSource(commonDsProperties);

        Map<Object, Object> allDS = new HashMap<>();
        allDS.put(TenantContext.DEFAULT_COMMON_TENANT, commonDS);

        tenantDsProperties.initAllDatasource(allDS);

        super.setDefaultTargetDataSource(commonDS);
        super.setTargetDataSources(allDS);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Long tenantId = TenantContext.getCurrentTenant();
        System.out.println("Determined TenantID is: " + tenantId);
        return tenantId;
    }

    private DataSource initCommonDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
}
