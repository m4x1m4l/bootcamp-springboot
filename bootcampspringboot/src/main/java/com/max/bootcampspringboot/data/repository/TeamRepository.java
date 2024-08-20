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

    @Query(value = "SELECT team_name,\n" +
            "         employee_name,\n" +
            "         skill_name,\n" +
            "         max_experienceLevel\n" +
            "FROM \n" +
            "    (SELECT t.name AS team_name,\n" +
            "         CONCAT(e.salutation,\n" +
            "         ' ', e.firstname, ' ', e.lastname) AS employee_name, s.name AS skill_name, k.experienceLevel AS max_experienceLevel, ROW_NUMBER()\n" +
            "        OVER (PARTITION BY t.name, s.name\n" +
            "    ORDER BY  k.experienceLevel DESC) AS rn\n" +
            "    FROM teams t\n" +
            "    JOIN employees e\n" +
            "        ON t.id = e.fk_team\n" +
            "    JOIN knowledges k\n" +
            "        ON e.id = k.fk_employee\n" +
            "    JOIN skills s\n" +
            "        ON k.fk_skill = s.id ) subquery\n" +
            "WHERE rn = 1\n" +
            "ORDER BY  team_name", nativeQuery = true)
    public List<String> test();

}
