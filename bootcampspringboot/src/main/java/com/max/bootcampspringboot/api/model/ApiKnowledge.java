package com.max.bootcampspringboot.api.model;

public class ApiKnowledge {
    private int experienceLevel;

    private int employeeId;

    private int skillId;

    public ApiKnowledge() {
    }

    public ApiKnowledge(int experienceLevel, int employeeId, int skillId) {
        this.experienceLevel = experienceLevel;
        this.employeeId = employeeId;
        this.skillId = skillId;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public int getEmployeeId() {
        return this.employeeId;
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
