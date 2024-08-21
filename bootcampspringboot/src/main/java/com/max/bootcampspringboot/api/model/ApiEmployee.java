package com.max.bootcampspringboot.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ApiEmployee {
    private Integer id;
    @NotBlank(message = "firstname should not be blank")
    @Size(min = 1, max = 50, message="Employee firstname must be between 1 and 50 characters")
    private String firstname;
    @NotBlank(message = "lastname should not be blank")
    @Size(min = 1, max = 50, message="Employee lastname must be between 1 and 50 characters")
    private String lastname;
    @NotBlank(message = "salutation should not be blank")
    @Size(min = 1, max = 20, message="Employee salutation must be between 1 and 20 characters")
    private String salutation;
    @NotEmpty(message="Employee birtDate must be given")
    private LocalDate birthdate;
    @NotNull(message="Employee teamId must be given")
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
