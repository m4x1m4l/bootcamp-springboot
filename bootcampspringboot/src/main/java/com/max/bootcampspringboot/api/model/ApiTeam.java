package com.max.bootcampspringboot.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApiTeam {
    private Integer id;
    @NotBlank(message = "name should not be blank")
    @Size(min = 1, max = 50, message="Team name must be between 1 and 50 characters")
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
