package com.autoever.multitenancy.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final TenantRepository tenantRepository;
    private final TenantSchemaInitializer tenantSchemaInitializer;

    @GetMapping("/admin/tenants")
    public List<Tenant> getTenants() {
        return tenantRepository.findAll();
    }

    @PostMapping("/admin/tenants")
    public Tenant addTenant(@RequestBody Tenant tenant) {
        tenant.setTenantType(TenantType.CPO);
        tenantRepository.save(tenant);
        tenantSchemaInitializer.init(tenant.getId());
        return tenant;
    }
}
