package com.max.bootcampspringboot.repository;

import com.max.bootcampspringboot.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
