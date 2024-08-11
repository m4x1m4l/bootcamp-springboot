package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.entity.Skill;
import com.max.bootcampspringboot.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final ServiceSkillMapper serviceSkillMapper;

    public SkillService(SkillRepository skillRepository,ServiceSkillMapper serviceSkillMapper) {
        this.skillRepository = skillRepository;
        this.serviceSkillMapper = serviceSkillMapper;
    }

    public ServiceSkill getSkill(int id) {
        return serviceSkillMapper.toServiceSkill(this.skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)));
    }

    public List<ServiceSkill> getAllSkills() {
        return serviceSkillMapper.toServiceSkillList( this.skillRepository.findAll());
    }

    public ServiceSkill addSkill(ServiceSkill skill) {
        return serviceSkillMapper.toServiceSkill( this.skillRepository.save(serviceSkillMapper.toSkill( skill)));
    }

    public Skill updateSkill(Skill skill) {
        Skill oldSkill = getSkill(skill.getId());
        oldSkill.setName(skill.getName());
        return skillRepository.save(oldSkill);

    }

    public void deleteSkill(int id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
        }
    }
}
