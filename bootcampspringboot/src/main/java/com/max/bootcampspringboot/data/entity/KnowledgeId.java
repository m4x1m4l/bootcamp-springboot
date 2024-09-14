package com.max.bootcampspringboot.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KnowledgeId implements Serializable {
    @Column(name = "fk_employee")
    private int employeeId;

    @Column(name = "fk_skill")
    private int skillId;

    public KnowledgeId() {
    }

    public KnowledgeId(int employeeId, int skillId) {
        this.employeeId = employeeId;
        this.skillId = skillId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeId that = (KnowledgeId) o;
        return employeeId == that.employeeId && skillId == that.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, skillId);
    }
}
