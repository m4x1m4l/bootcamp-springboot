package com.max.bootcampspringboot.service.mapper;

import com.max.bootcampspringboot.data.entity.Employee;
import com.max.bootcampspringboot.data.entity.Knowledge;
import com.max.bootcampspringboot.data.entity.KnowledgeId;
import com.max.bootcampspringboot.service.model.ServiceKnowledge;

import java.util.List;
import java.util.Optional;

public class ServiceKnowledgeMapper {
    public static ServiceKnowledge toServiceKnowledge(Knowledge knowledge) {
        ServiceKnowledge serviceKnowledge = new ServiceKnowledge();
        serviceKnowledge.setEmployeeId(knowledge.getEmployee().getId());
        serviceKnowledge.setSkillId(knowledge.getSkill().getId());
        serviceKnowledge.setExperienceLevel(knowledge.getExperienceLevel());
        return serviceKnowledge;
    }

    public static List<ServiceKnowledge> toServiceKnowledge(List<Knowledge> knowledge){
        return knowledge.stream().map(ServiceKnowledgeMapper::toServiceKnowledge).toList();
    }

    public Knowledge toKnowledge(ServiceKnowledge serviceKnowledge) {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(new KnowledgeId(serviceKnowledge.getEmployeeId(), serviceKnowledge.getSkillId()));
        knowledge.setExperienceLevel(serviceKnowledge.getExperienceLevel());
        return knowledge;
    }
}
