package com.max.bootcampspringboot.service.model;

public class ServiceSkill {
    private Integer id;
    private String name;

    public ServiceSkill() {
    }

    public ServiceSkill(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
