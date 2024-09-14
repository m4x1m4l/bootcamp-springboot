package com.max.bootcampspringboot.api.mapper;

import com.max.bootcampspringboot.api.model.ApiKnowledge;
import com.max.bootcampspringboot.service.model.ServiceKnowledge;

import java.util.List;

public class ApiKnowledgeMapper {

    public static ApiKnowledge toApiKnowledge(ServiceKnowledge serviceKnowledge) {
        ApiKnowledge apiKnowledge = new ApiKnowledge();
        apiKnowledge.setEmployeeId(serviceKnowledge.getEmployeeId());
        apiKnowledge.setExperienceLevel(serviceKnowledge.getExperienceLevel());
        apiKnowledge.setSkillId(serviceKnowledge.getSkillId());
        return apiKnowledge;
    }
    public static List<ApiKnowledge> toApiKnowledge(List<ServiceKnowledge> serviceKnowledge) {
        return serviceKnowledge.stream().map(ApiKnowledgeMapper::toApiKnowledge).toList();
    }

    public static ServiceKnowledge toServiceKnowledge(ApiKnowledge apiKnowledge) {
        ServiceKnowledge serviceKnowledge = new ServiceKnowledge();
        serviceKnowledge.setExperienceLevel(apiKnowledge.getExperienceLevel());
        serviceKnowledge.setEmployeeId(apiKnowledge.getEmployeeId());
        serviceKnowledge.setSkillId(apiKnowledge.getSkillId());
        return serviceKnowledge;
    }
}
