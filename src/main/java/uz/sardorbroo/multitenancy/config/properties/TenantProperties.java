package uz.sardorbroo.multitenancy.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@AllArgsConstructor
@ConfigurationProperties(prefix = "tenant")
public class TenantProperties {

    private final List<TenantDatasource> datasource = new ArrayList<>();

    public void initAllDatasource(Map<Object, Object> map) {

        this.datasource.forEach(datasource -> {
            map.put(datasource.getId(), datasource.build());
        });

    }

    @Data
    @AllArgsConstructor
    public static class TenantDatasource {

        private Long id;

        private String url;

        private String username;

        private String password;

        private String driver;

        public DataSource build() {

            DataSourceBuilder builder = DataSourceBuilder.create();
            builder.url(url);
            builder.username(username);
            builder.password(password);
            builder.driverClassName(driver);

            return builder.build();
        }
    }
}
