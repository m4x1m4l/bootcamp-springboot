package com.max.bootcampspringboot.api.mapper;

import com.max.bootcampspringboot.api.model.ApiSkill;
import com.max.bootcampspringboot.service.model.ServiceSkill;
import org.springframework.stereotype.Component;

import java.util.List;

public class ApiSkillMapper {
    public static ApiSkill toApiSkill(ServiceSkill skill) {
        ApiSkill apiSkill = new ApiSkill();
        apiSkill.setId(skill.getId());
        apiSkill.setName(skill.getName());
        return apiSkill;
    }

    public static List<ApiSkill> toApiSkill(List<ServiceSkill> allSkills) {
        return allSkills.stream().map(ApiSkillMapper::toApiSkill).toList();
    }

    public static ServiceSkill toServiceSkill(ApiSkill skill) {
        ServiceSkill serviceSkill = new ServiceSkill();
        serviceSkill.setId(skill.getId());
        serviceSkill.setName(skill.getName());
        return serviceSkill;
    }
}
