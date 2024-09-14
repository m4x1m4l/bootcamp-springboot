package com.max.bootcampspringboot.service.mapper;

import com.max.bootcampspringboot.data.entity.Knowledge;
import com.max.bootcampspringboot.data.entity.KnowledgeId;
import com.max.bootcampspringboot.service.model.ServiceKnowledge;

import java.util.List;

public class ServiceKnowledgeMapper {
    public static ServiceKnowledge toServiceKnowledge(Knowledge knowledge) {
        ServiceKnowledge serviceKnowledge = new ServiceKnowledge();
        serviceKnowledge.setEmployeeId(knowledge.getId().getEmployeeId());
        serviceKnowledge.setSkillId(knowledge.getId().getSkillId());
        serviceKnowledge.setExperienceLevel(knowledge.getExperienceLevel());
        return serviceKnowledge;
    }

    public static List<ServiceKnowledge> toServiceKnowledge(List<Knowledge> knowledge){
        return knowledge.stream().map(ServiceKnowledgeMapper::toServiceKnowledge).toList();
    }

    public static Knowledge toKnowledge(ServiceKnowledge serviceKnowledge) {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(new KnowledgeId(serviceKnowledge.getEmployeeId(), serviceKnowledge.getSkillId()));
        knowledge.setExperienceLevel(serviceKnowledge.getExperienceLevel());
        return knowledge;
    }
}
