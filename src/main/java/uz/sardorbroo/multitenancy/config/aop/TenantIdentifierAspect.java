package uz.sardorbroo.multitenancy.config.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uz.sardorbroo.multitenancy.config.TenantContext;

@Aspect
public class TenantIdentifierAspect {

    private static final String TENANT_ID_HEADER_NAME = "X-Tenant-Id";

    @Pointcut("execution(* uz.sardorbroo.multitenancy.repository.common..*(..))")
    public void callingCommonRepository() {
    }

    @Around("callingCommonRepository()")
    public Object callingCommonDatasource(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Common repository has called");

        try {
            TenantContext.setCurrentTenant(TenantContext.DEFAULT_COMMON_TENANT);

            return pjp.proceed();
        } finally {
            TenantContext.clear();
        }
    }

    @Pointcut("execution(* uz.sardorbroo.multitenancy.repository.tenancy..*(..))")
    public void callingTenancyRepository() {
    }

    @Around("callingTenancyRepository()")
    public Object callingTenantDatasource(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Tenant repository has called");

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();

            Long tenantId = getTenantIdFromRequest(request);

            TenantContext.setCurrentTenant(tenantId);

            return pjp.proceed();
        } finally {
            TenantContext.clear();
        }
    }

    private Long getTenantIdFromRequest(HttpServletRequest request) {

        String tenantIdAsString = request.getHeader(TENANT_ID_HEADER_NAME);
        if (tenantIdAsString == null || tenantIdAsString.isEmpty()) {
            throw new RuntimeException("Tenant ID is not set!");
        }

        try {
            return Long.parseLong(tenantIdAsString);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Tenant ID is not number!");
        }
    }
}
