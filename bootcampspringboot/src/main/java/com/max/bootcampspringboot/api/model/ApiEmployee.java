package com.max.bootcampspringboot.api.model;

import java.time.LocalDate;

public class ApiEmployee {
    private Integer id;

    private String firstname;
    private String lastname;
    private String salutation;
    private LocalDate birthdate;

    private int teamId;
    public ApiEmployee() {
    }

    public ApiEmployee(Integer id, String firstname, int teamId, LocalDate birthdate, String salutation, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.teamId = teamId;
        this.birthdate = birthdate;
        this.salutation = salutation;
        this.lastname = lastname;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
}
