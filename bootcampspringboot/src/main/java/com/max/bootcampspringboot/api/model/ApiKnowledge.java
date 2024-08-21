package com.max.bootcampspringboot.api.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ApiKnowledge {
    @NotNull
    @Min(value = 0, message = "Knowledge experienceLevel must be at least 0")
    @Max(value = 10, message = "Knowledge experienceLevel must be at most 10")
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
