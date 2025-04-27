package uz.sardorbroo.multitenancy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import uz.sardorbroo.multitenancy.config.aop.TenantIdentifierAspect;

@Configuration
@EnableAspectJAutoProxy
public class TenantIdentifierAspectConfiguration {

    @Bean
    public TenantIdentifierAspect initAspect() {
        return new TenantIdentifierAspect();
    }

}
