package com.jeesite.modules.oy_order_management.entity;

public class AuditStatus {
    private String value;

    public AuditStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
