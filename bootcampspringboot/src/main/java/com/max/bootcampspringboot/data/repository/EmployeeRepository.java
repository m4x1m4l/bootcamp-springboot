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


}
