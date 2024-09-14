package com.max.bootcampspringboot.service.model;

import com.max.bootcampspringboot.data.entity.Employee;

public class ServiceTeam {
    private Integer id;
    private String name;
    private Employee teamlead;


    public ServiceTeam() {
    }

    public ServiceTeam(Integer id, String name, Employee teamlead) {
        this.id = id;
        this.name = name;
        this.teamlead = teamlead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getTeamlead() {
        return teamlead;
    }

    public void setTeamlead(Employee teamlead) {
        this.teamlead = teamlead;
    }
}
