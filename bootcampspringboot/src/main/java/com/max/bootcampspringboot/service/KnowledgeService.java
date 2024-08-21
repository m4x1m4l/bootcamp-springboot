package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.KnowledgeId;
import com.max.bootcampspringboot.data.repository.KnowledgeRepository;
import com.max.bootcampspringboot.service.mapper.ServiceKnowledgeMapper;
import com.max.bootcampspringboot.service.model.ServiceKnowledge;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeService {
    private final KnowledgeRepository knowledgeRepository;

    public KnowledgeService(KnowledgeRepository knowledgeRepository) {
        this.knowledgeRepository = knowledgeRepository;
    }

    public ServiceKnowledge getKnowledge(int employeeId, int skillId){
        KnowledgeId knowledgeId = new KnowledgeId(employeeId, skillId);
        return ServiceKnowledgeMapper.toServiceKnowledge(this.knowledgeRepository.findById(knowledgeId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + knowledgeId)));
    }

    public List<ServiceKnowledge> getAllKnowledgesByEmployeeId(int employeeId){
        return ServiceKnowledgeMapper.toServiceKnowledge(this.knowledgeRepository.findByEmployeeId(employeeId));
    }

    public ServiceKnowledge addKnowledge(ServiceKnowledge knowledge){
        return ServiceKnowledgeMapper.toServiceKnowledge(this.knowledgeRepository.save(ServiceKnowledgeMapper.toKnowledge(knowledge)));
    }

    public ServiceKnowledge updateKnowledge(ServiceKnowledge knowledge){
        ServiceKnowledge oldKnowledge = getKnowledge(knowledge.getEmployeeId(), knowledge.getSkillId());
        oldKnowledge.setSkillId(knowledge.getSkillId());
        oldKnowledge.setEmployeeId(knowledge.getEmployeeId());
        oldKnowledge.setExperienceLevel(knowledge.getExperienceLevel());
        return ServiceKnowledgeMapper.toServiceKnowledge(knowledgeRepository.save(ServiceKnowledgeMapper.toKnowledge(oldKnowledge)));
    }

    public void deleteKnowledge(int employeeId, int skillId) {
        KnowledgeId knowledgeId = new KnowledgeId(employeeId, skillId);
        if(knowledgeRepository.existsById(knowledgeId)){
            knowledgeRepository.deleteById(knowledgeId);
        }
    }



}
