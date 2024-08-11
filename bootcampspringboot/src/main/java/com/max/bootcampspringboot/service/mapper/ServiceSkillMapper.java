package com.max.bootcampspringboot.service.mapper;

import com.max.bootcampspringboot.data.entity.Skill;
import com.max.bootcampspringboot.service.model.ServiceSkill;

import java.util.List;

public class ServiceSkillMapper {


    public static ServiceSkill toServiceSkill(Skill skill) {
        ServiceSkill serviceSkill = new ServiceSkill();
        serviceSkill.setName(skill.getName());
        serviceSkill.setId(skill.getId());
        return serviceSkill;
    }

    public static  List<ServiceSkill> toServiceSkillList(List<Skill> skills) {
        return skills.stream().map(ServiceSkillMapper::toServiceSkill).toList();
    }

    public static Skill toSkill(ServiceSkill serviceSkill) {
        Skill skill = new Skill();
        skill.setId(serviceSkill.getId());
        skill.setName(serviceSkill.getName());
        return skill;
    }
}
