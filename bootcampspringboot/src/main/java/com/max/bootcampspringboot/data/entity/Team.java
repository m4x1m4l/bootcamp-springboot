package com.max.bootcampspringboot.data.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToOne()
    @JoinColumn(name = "teamlead_id")
    private Employee teamLead;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees;

    public Team() {
    }

    public Team(Employee teamLead, List<Employee> employees, Integer id, String name) {
        this.teamLead = teamLead;
        this.employees = employees;
        this.id = id;
        this.name = name;
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

    public Employee getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(Employee teamLead) {
        this.teamLead = teamLead;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
