package com.autoever.multitenancy.config;

import lombok.Getter;

@Getter
public enum ConfigConstants {
    ADMIN_TENANT_ID("ADMIN"),
    TENANT_ID_KEY("TENANT_ID")
    ;

    private final String value;

    ConfigConstants(String value) {
        this.value = value;
    }

}
