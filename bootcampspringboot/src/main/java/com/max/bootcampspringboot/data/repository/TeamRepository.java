package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query("SELECT t.name, COUNT(e) FROM Employee e JOIN e.team t GROUP BY t.id, t.name HAVING COUNT(e) > :employeeCount ORDER BY t.name")
    public List<String> findTeamsWithMoreThanXEmployees(int employeeCount);

    @Query("SELECT t.name, COUNT(e) FROM Team t JOIN t.employees e GROUP BY t.name ORDER BY COUNT(e) DESC LIMIT 1")
    public String findLargestTeam();

    @Query("SELECT s.name FROM Team t JOIN t.employees e JOIN e.knowledges k JOIN k.skill s")
    public List<String> findSkillsInTeam(String skill);

}
