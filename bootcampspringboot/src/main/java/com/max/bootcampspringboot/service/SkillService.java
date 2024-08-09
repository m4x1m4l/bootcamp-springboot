package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.Skill;
import com.max.bootcampspringboot.data.repository.SkillRepository;
import com.max.bootcampspringboot.service.mapper.ServiceSkillMapper;
import com.max.bootcampspringboot.service.model.ServiceSkill;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    public ServiceSkill createSkill(ServiceSkill skill) {
        return serviceSkillMapper.toServiceSkill( this.skillRepository.save(serviceSkillMapper.toSkill( skill)));
    }

    public ServiceSkill updateSkill(ServiceSkill skill) {
        return this.skillRepository.findById(skill.getId()).map(oldSkill -> {
            oldSkill.setName((skill.getName()));
            return serviceSkillMapper.toServiceSkill(skillRepository.save(oldSkill));
        }).orElseThrow(() ->  new EntityNotFoundException("Skill not found with id: " + skill.getId()));
    }

    public void deleteSkill(int id) {
        Skill toDelete = this.skillRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Skill not found with id: " + id));
        this.skillRepository.deleteById(id);
    }
}
