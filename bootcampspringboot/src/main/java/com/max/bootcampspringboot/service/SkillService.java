package com.max.bootcampspringboot.service;
import com.max.bootcampspringboot.data.repository.SkillRepository;
import com.max.bootcampspringboot.service.mapper.ServiceSkillMapper;
import com.max.bootcampspringboot.service.model.ServiceSkill;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final ServiceSkillMapper serviceSkillMapper = new ServiceSkillMapper();

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
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

    public ServiceSkill updateSkill(ServiceSkill skill) {
        ServiceSkill oldSkill = getSkill(skill.getId());
        oldSkill.setName(skill.getName());
        return serviceSkillMapper.toServiceSkill(skillRepository.save(serviceSkillMapper.toSkill(oldSkill)));

    }

    public void deleteSkill(int id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
        }
    }
}
