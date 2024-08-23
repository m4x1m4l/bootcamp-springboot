package com.max.bootcampspringboot.data.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    private String salutation;
    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name = "fk_team")
    private Team team;

    @OneToMany(mappedBy = "employee")
    private List<Knowledge> knowledges;

    @Column(name = "trump_quote")
    private String trumpQuote;

    public Employee() {
    }

    public Employee(Integer id, String firstname, String lastname, String salutation, LocalDate birthdate, Team team, List<Knowledge> knowledges, String trumpQuote) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salutation = salutation;
        this.birthdate = birthdate;
        this.team = team;
        this.knowledges = knowledges;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(List<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }

    public String getTrumpQuote() {
        return trumpQuote;
    }

    public void setTrumpQuote(String trumpQuote) {
        this.trumpQuote = trumpQuote;
    }
}
