package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
