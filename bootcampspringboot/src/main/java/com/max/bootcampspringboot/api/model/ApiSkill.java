package com.max.bootcampspringboot.api.model;


public class ApiSkill {
    private Integer id;
    private String name;

    public ApiSkill() {
    }

    public ApiSkill(Integer id, String name) {
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
