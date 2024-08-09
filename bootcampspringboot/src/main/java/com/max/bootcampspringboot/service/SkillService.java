package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.Skill;
import com.max.bootcampspringboot.data.repository.SkillRepository;
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
        return this.skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<Skill> getAllSkills() {
        return this.skillRepository.findAll();
    }

    public Skill createSkill(Skill skill) {
        return this.skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill) {
        return this.skillRepository.findById(skill.getId()).map(oldSkill -> {
            oldSkill.setName((skill.getName()));
            return skillRepository.save(oldSkill);
        }).orElseThrow(() ->  new EntityNotFoundException("Skill not found with id: " + skill.getId()));
    }

    public void deleteSkill(int id) {
        Skill toDelete = this.skillRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Skill not found with id: " + id));
        this.skillRepository.delete(toDelete);
    }
}
