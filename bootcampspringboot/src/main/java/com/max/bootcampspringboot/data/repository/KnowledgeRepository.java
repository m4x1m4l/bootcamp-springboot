package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Knowledge;
import com.max.bootcampspringboot.data.entity.KnowledgeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface KnowledgeRepository extends JpaRepository<Knowledge, KnowledgeId> {
    List<Knowledge> findByEmployeeId(int employeeId);
}
