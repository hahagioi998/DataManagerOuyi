package com.jeesite.modules.oy_order_management.entity;

public class Carrieroperator {
    private int id;
    private String name;

    public Carrieroperator(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
