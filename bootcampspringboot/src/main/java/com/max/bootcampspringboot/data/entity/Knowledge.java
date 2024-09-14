package com.max.bootcampspringboot.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "knowledges")
public class Knowledge {
    @EmbeddedId
    private KnowledgeId id;

    @Column(name = "experiencelevel")
    private int experienceLevel;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "fk_employee")
    private Employee employee;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "fk_skill")
    private Skill skill;

    public Knowledge() {
    }

    public Knowledge(KnowledgeId id, int experienceLevel, Employee employee, Skill skill) {
        this.id = id;
        this.experienceLevel = experienceLevel;
        this.employee = employee;
        this.skill = skill;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public KnowledgeId getId() {
        return id;
    }

    public void setId(KnowledgeId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", experienceLevel=" + experienceLevel +
                ", employee=" + employee +
                ", skill=" + skill +
                '}';
    }
}
