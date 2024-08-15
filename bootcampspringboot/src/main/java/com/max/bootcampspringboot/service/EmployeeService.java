package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.repository.EmployeeRepository;
import com.max.bootcampspringboot.data.repository.EmployeeRepository;
import com.max.bootcampspringboot.service.mapper.ServiceEmployeeMapper;
import com.max.bootcampspringboot.service.mapper.ServiceEmployeeMapper;
import com.max.bootcampspringboot.service.model.ServiceEmployee;
import com.max.bootcampspringboot.service.model.ServiceEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ServiceEmployeeMapper serviceEmployeeMapper = new ServiceEmployeeMapper();

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ServiceEmployee getEmployee(int id) {
        return serviceEmployeeMapper.toServiceEmployee(this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)));
    }

    public List<ServiceEmployee> getAllEmployees() {
        return serviceEmployeeMapper.toServiceEmployee( this.employeeRepository.findAll());
    }

    public ServiceEmployee addEmployee(ServiceEmployee employee) {
        return serviceEmployeeMapper.toServiceEmployee( this.employeeRepository.save(serviceEmployeeMapper.toEmployee( employee)));
    }

    public ServiceEmployee updateEmployee(ServiceEmployee employee) {
        ServiceEmployee oldEmployee = getEmployee(employee.getId());
        oldEmployee.setFirstname(employee.getFirstname());
        oldEmployee.setLastname(employee.getLastname());
        oldEmployee.setBirthdate(employee.getBirthdate());
        oldEmployee.setSalutation(employee.getSalutation());

        return serviceEmployeeMapper.toServiceEmployee(employeeRepository.save(serviceEmployeeMapper.toEmployee(oldEmployee)));

    }

    public void deleteEmployee(int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }
    }
}
