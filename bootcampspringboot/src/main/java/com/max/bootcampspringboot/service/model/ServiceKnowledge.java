package com.max.bootcampspringboot.service.model;

import com.max.bootcampspringboot.data.entity.KnowledgeId;

public class ServiceKnowledge {
    private int experienceLevel;

    private int employeeId;

    private int skillId;

    public ServiceKnowledge() {

    }

    public ServiceKnowledge(int skillId, int employeeId, int experienceLevel) {
        this.skillId = skillId;
        this.employeeId = employeeId;
        this.experienceLevel = experienceLevel;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
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
}
