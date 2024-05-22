package com.autoever.multitenancy.config;

import org.springframework.stereotype.Component;

@Component
public class SchemaBuilder {


    public String getAdminSchema() {
        return getTenantSchema(ConfigConstants.ADMIN_TENANT_ID.getValue());
    }

    public String getTenantSchema(String tenantId) {
        if (tenantId == null || tenantId.isEmpty())
            return getAdminSchema();
        return "TENANT_" + tenantId;
    }
}
