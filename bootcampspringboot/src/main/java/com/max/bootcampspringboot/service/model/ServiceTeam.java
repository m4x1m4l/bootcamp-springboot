package com.max.bootcampspringboot.service.model;

public class ServiceTeam {
    private Integer id;
    private String name;
    private int teamleadId;


    public ServiceTeam() {
    }

    public ServiceTeam(Integer id, String name, int teamleadId) {
        this.id = id;
        this.name = name;
        this.teamleadId = teamleadId;
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

    public int getTeamleadId() {
        return teamleadId;
    }

    public void setTeamleadId(int teamleadId) {
        this.teamleadId = teamleadId;
    }
}
