package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.KnowledgeId;
import com.max.bootcampspringboot.data.repository.KnowledgeRepository;
import com.max.bootcampspringboot.data.repository.SkillRepository;
import com.max.bootcampspringboot.service.mapper.ServiceKnowledgeMapper;
import com.max.bootcampspringboot.service.model.ServiceKnowledge;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeService {
    private final KnowledgeRepository knowledgeRepository;
    private final ServiceKnowledgeMapper serviceKnowledgeMapper = new ServiceKnowledgeMapper();

    public KnowledgeService(KnowledgeRepository knowledgeRepository) {
        this.knowledgeRepository = knowledgeRepository;
    }

    public ServiceKnowledge getKnowledge(int employeeId, int skillId){
        KnowledgeId knowledgeId = new KnowledgeId(employeeId, skillId);
        return serviceKnowledgeMapper.toServiceKnowledge(this.knowledgeRepository.findById(knowledgeId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + knowledgeId)));
    }

    public List<ServiceKnowledge> getAllKnowledges(){
        return serviceKnowledgeMapper.toServiceKnowledge(this.knowledgeRepository.findAll());
    }

    public ServiceKnowledge addKnowledge(ServiceKnowledge knowledge){
        return serviceKnowledgeMapper.toServiceKnowledge(this.knowledgeRepository.save(serviceKnowledgeMapper.toKnowledge(knowledge)));
    }

    public ServiceKnowledge updateKnowledge(ServiceKnowledge knowledge){
        ServiceKnowledge oldKnowledge = getKnowledge(knowledge.getEmployeeId(), knowledge.getSkillId());
        oldKnowledge.setSkillId(knowledge.getSkillId());
        oldKnowledge.setEmployeeId(knowledge.getEmployeeId());
        oldKnowledge.setExperienceLevel(knowledge.getExperienceLevel());
        return serviceKnowledgeMapper.toServiceKnowledge(knowledgeRepository.save(serviceKnowledgeMapper.toKnowledge(oldKnowledge)));
    }

    public void deleteSkill(int employeeId, int skillId) {
        KnowledgeId knowledgeId = new KnowledgeId(employeeId, skillId);
        if(knowledgeRepository.existsById(knowledgeId)){
            knowledgeRepository.deleteById(knowledgeId);
        }
    }



}
