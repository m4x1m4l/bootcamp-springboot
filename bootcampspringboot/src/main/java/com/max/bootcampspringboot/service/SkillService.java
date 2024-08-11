package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.entity.Skill;
import com.max.bootcampspringboot.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getSkill(int id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
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
