package com.max.bootcampspringboot.service.model;

import com.max.bootcampspringboot.data.entity.Team;

import java.time.LocalDate;

public class ServiceEmployee {
    private Integer id;

    private String firstname;
    private String lastname;
    private String salutation;
    private LocalDate birthdate;

    private Team team;

    public ServiceEmployee() {
    }

    public ServiceEmployee(Integer id, String firstname, String lastname, String salutation, LocalDate birthdate, Team team) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salutation = salutation;
        this.birthdate = birthdate;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
