package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
