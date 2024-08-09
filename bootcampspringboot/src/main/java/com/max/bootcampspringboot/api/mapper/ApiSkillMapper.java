package com.max.bootcampspringboot.api.mapper;

import com.max.bootcampspringboot.api.model.ApiSkill;
import com.max.bootcampspringboot.data.entity.Skill;
import com.max.bootcampspringboot.service.model.ServiceSkill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiSkillMapper {
    public ApiSkill toApiSkill(ServiceSkill skill) {
        ApiSkill apiSkill = new ApiSkill();
        apiSkill.setId(skill.getId());
        apiSkill.setName(skill.getName());
        return apiSkill;
    }

    public List<ApiSkill> toApiSkillList(List<ServiceSkill> allSkills) {
        return allSkills.stream().map(this::toApiSkill).toList();
    }

    public ServiceSkill toServiceSkill(ApiSkill skill) {
        ServiceSkill serviceSkill = new ServiceSkill();
        serviceSkill.setId(skill.getId());
        serviceSkill.setName(skill.getName());
        return serviceSkill;
    }
}
