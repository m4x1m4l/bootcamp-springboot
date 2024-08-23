package com.max.bootcampspringboot.service.model;

import java.time.LocalDate;

public class ServiceEmployee {
    private Integer id;

    private String firstname;
    private String lastname;
    private String salutation;
    private LocalDate birthdate;

    private int teamId;

    private String trumpQuote;

    public ServiceEmployee() {
    }

    public ServiceEmployee(Integer id, String firstname, String lastname, String salutation, LocalDate birthdate, int teamId, String trumpQuote) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salutation = salutation;
        this.birthdate = birthdate;
        this.teamId = teamId;
        this.trumpQuote = trumpQuote;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTrumpQuote() {
        return trumpQuote;
    }

    public void setTrumpQuote(String trumpQuote) {
        this.trumpQuote = trumpQuote;
    }
}
