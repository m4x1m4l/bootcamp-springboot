package com.max.bootcampspringboot.api.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApiSkill {
    private Integer id;
    @NotBlank(message = "name should not be blank")
    @Size(min = 1, max = 50, message="Skill name must be between 1 and 50 characters")
    private String name;

    public ApiSkill() {
    }

    public ApiSkill(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
