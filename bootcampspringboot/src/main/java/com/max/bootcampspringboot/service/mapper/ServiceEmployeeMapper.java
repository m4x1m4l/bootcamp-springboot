package com.max.bootcampspringboot.service.mapper;

import com.max.bootcampspringboot.data.entity.Employee;
import com.max.bootcampspringboot.service.model.ServiceEmployee;

import java.util.List;

public class ServiceEmployeeMapper {
    public static ServiceEmployee toServiceEmployee(Employee employee) {
        ServiceEmployee serviceEmployee = new ServiceEmployee();
        serviceEmployee.setSalutation(employee.getSalutation());
        serviceEmployee.setBirthdate(employee.getBirthdate());
        serviceEmployee.setId(employee.getId());
        serviceEmployee.setLastname(employee.getLastname());
        serviceEmployee.setFirstname(employee.getFirstname());
        serviceEmployee.setTeam(employee.getTeam());
        return serviceEmployee;
    }

    public static List<ServiceEmployee> toServiceEmployee(List<Employee> employee) {
        return employee.stream().map(ServiceEmployeeMapper::toServiceEmployee).toList();
    }

    public static Employee toEmployee(ServiceEmployee serviceEmployee) {
        Employee employee = new Employee();
        employee.setSalutation(serviceEmployee.getSalutation());
        employee.setBirthdate(serviceEmployee.getBirthdate());
        employee.setId(serviceEmployee.getId());
        employee.setLastname(serviceEmployee.getLastname());
        employee.setFirstname(serviceEmployee.getFirstname());
        employee.setTeam(serviceEmployee.getTeam());

        return employee;
    }
}
