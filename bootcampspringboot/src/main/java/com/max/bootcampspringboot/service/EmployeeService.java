package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.Employee;
import com.max.bootcampspringboot.data.entity.Team;
import com.max.bootcampspringboot.data.repository.EmployeeRepository;
import com.max.bootcampspringboot.data.repository.TeamRepository;
import com.max.bootcampspringboot.exception.NotFoundException;
import com.max.bootcampspringboot.service.mapper.ServiceEmployeeMapper;
import com.max.bootcampspringboot.service.model.ServiceEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    public ServiceEmployee getEmployee(int id) {
        return ServiceEmployeeMapper.toServiceEmployee(this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)));
    }

    public List<ServiceEmployee> getAllEmployees() {
        return ServiceEmployeeMapper.toServiceEmployee( this.employeeRepository.findAll());
    }

    public ServiceEmployee addEmployee(ServiceEmployee employee) throws NotFoundException {

        Employee employeeToSave = ServiceEmployeeMapper.toEmployee(employee);
        //add Team
        Team teamOfEmployee = teamRepository.findById(employee.getTeamId())
                .orElseThrow(() -> new NotFoundException("Team ID of Employee not found: " + employee.getTeamId()));
        employeeToSave.setTeam(teamOfEmployee);

        return ServiceEmployeeMapper.toServiceEmployee( this.employeeRepository.save(employeeToSave));
    }

    public ServiceEmployee updateEmployee(int employeeId, ServiceEmployee employee) {
        if(employeeId != employee.getId()) throw new RuntimeException("Id and Id in Object do not equal!");

        Employee employeeToSave = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee ID not found: " + employee.getId()));

        employeeToSave.setFirstname(employee.getFirstname());
        employeeToSave.setLastname(employee.getLastname());
        employeeToSave.setBirthdate(employee.getBirthdate());
        employeeToSave.setSalutation(employee.getSalutation());

        //add Team
        Team teamOfEmployee = teamRepository.findById(employee.getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Team ID of Employee not found: " + employee.getTeamId()));

        employeeToSave.setTeam(teamOfEmployee);

        return ServiceEmployeeMapper.toServiceEmployee(employeeRepository.save(employeeToSave));

    }

    public void deleteEmployee(int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }
    }
}
