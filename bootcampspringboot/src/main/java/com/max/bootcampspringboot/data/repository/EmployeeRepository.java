package com.max.bootcampspringboot.data.repository;

import com.max.bootcampspringboot.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
