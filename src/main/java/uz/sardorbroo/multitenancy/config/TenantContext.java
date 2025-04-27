package uz.sardorbroo.multitenancy.config;

public class TenantContext {

    public static final Long DEFAULT_COMMON_TENANT = -1L;
    private static final ThreadLocal<Long> CURRENT_TENANT = new ThreadLocal<>();

    public static Long getCurrentTenant() {
        return CURRENT_TENANT.get();
    }

    public static void setCurrentTenant(Long tenant) {
        CURRENT_TENANT.set(tenant);
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }
}
