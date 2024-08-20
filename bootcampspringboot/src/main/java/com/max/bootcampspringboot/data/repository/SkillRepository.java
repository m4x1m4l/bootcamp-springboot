package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    @Query("SELECT s.name, COUNT(k) from Skill s join s.knowledges k GROUP BY s.name, k ORDER BY COUNT(k) ASC LIMIT 1")
    public String findRarestSkill();

    @Query("SELECT s.name, AVG(k.experienceLevel) AS avgskill FROM Skill s join s.knowledges k GROUP BY s.name ORDER BY avgskill DESC")
    public List<String> findAverageSkillPerSkill();
}
