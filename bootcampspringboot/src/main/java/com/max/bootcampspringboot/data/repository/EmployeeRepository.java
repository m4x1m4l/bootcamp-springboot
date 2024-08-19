package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("Select e.firstname from Employee e join e.team t where t.name = :teamName")
    List<String> findFirstnameByTeamName(@Param("teamName")String teamName);


    @Query("SELECT t.name, COUNT(e) FROM Employee e JOIN e.team t GROUP BY t.id, t.name ORDER BY t.id")
    public List<String> findEmployeeCountPerTeam();

    @Query("SELECT e FROM Employee e JOIN e.knowledges k GROUP BY e ORDER BY COUNT(k) DESC")
    public List<Employee> findEmployeesSortedPerSkillCount();

    @Query(value = "SELECT employees.firstname || ' ' || employees.lastname AS name, SUM(knowledges.experiencelevel) AS " +
            "skillpoints FROM knowledges INNER JOIN employees ON employees.id = knowledges.fk_employee GROUP BY " +
            "knowledges.fk_employee, employees.firstname, employees.lastname ORDER BY skillpoints DESC", nativeQuery = true)
    public List<String> findEmployeeSkillSum();

    @Query("SELECT e from Employee e JOIN e.knowledges k JOIN k.skill s WHERE s.name =:skillName")
    public List<Employee> findEmployeesBySkillName(@Param("skillName")String skillName);

}
