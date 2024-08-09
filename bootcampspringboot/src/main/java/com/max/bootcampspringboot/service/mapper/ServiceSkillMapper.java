package com.max.bootcampspringboot.service.mapper;

import com.max.bootcampspringboot.api.model.ApiSkill;
import com.max.bootcampspringboot.data.entity.Skill;
import com.max.bootcampspringboot.service.model.ServiceSkill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSkillMapper {


    public ServiceSkill toServiceSkill(Skill skill) {
        ServiceSkill serviceSkill = new ServiceSkill();
        serviceSkill.setName(skill.getName());
        serviceSkill.setId(skill.getId());
        return serviceSkill;
    }

    public List<ServiceSkill> toServiceSkillList(List<Skill> skills) {
        return skills.stream().map(this::toServiceSkill).toList();
    }

    public Skill toSkill(ServiceSkill serviceSkill) {
        Skill skill = new Skill();
        skill.setId(serviceSkill.getId());
        skill.setName(serviceSkill.getName());
        return skill;
    }
}
