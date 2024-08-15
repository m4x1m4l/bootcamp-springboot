package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Knowledge;
import com.max.bootcampspringboot.data.entity.KnowledgeId;
import com.max.bootcampspringboot.data.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface KnowledgeRepository extends JpaRepository<Knowledge, KnowledgeId> {

}
