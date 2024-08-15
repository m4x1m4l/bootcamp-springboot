package com.max.bootcampspringboot.api.model;

public class ApiTeam {
    private Integer id;
    private String name;
    private int teamleadId;

    public ApiTeam() {
    }

    public ApiTeam(String name, int teamleadId, Integer id) {
        this.name = name;
        this.teamleadId = teamleadId;
        this.id = id;
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
