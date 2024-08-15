package com.max.bootcampspringboot.api.mapper;

import com.max.bootcampspringboot.api.model.ApiEmployee;
import com.max.bootcampspringboot.data.entity.Employee;
import com.max.bootcampspringboot.service.model.ServiceEmployee;

import java.util.List;

public class ApiEmployeeMapper {

    public static ApiEmployee toApiEmployee(ServiceEmployee serviceEmployee) {
        ApiEmployee apiEmployee = new ApiEmployee();
        apiEmployee.setSalutation(serviceEmployee.getSalutation());
        apiEmployee.setBirthdate(serviceEmployee.getBirthdate());
        apiEmployee.setId(serviceEmployee.getId());
        apiEmployee.setLastname(serviceEmployee.getLastname());
        apiEmployee.setFirstname(serviceEmployee.getFirstname());
        apiEmployee.setTeamId(serviceEmployee.getTeamId());
        return apiEmployee;
    }
    public static List<ApiEmployee> toApiEmployee(List<ServiceEmployee> employee) {
        return employee.stream().map(ApiEmployeeMapper::toApiEmployee).toList();
    }

    public static ServiceEmployee toServiceEmployee(ApiEmployee apiEmployee) {
        ServiceEmployee serviceEmployee = new ServiceEmployee();
        serviceEmployee.setSalutation(apiEmployee.getSalutation());
        serviceEmployee.setBirthdate(apiEmployee.getBirthdate());
        serviceEmployee.setId(apiEmployee.getId());
        serviceEmployee.setLastname(apiEmployee.getLastname());
        serviceEmployee.setFirstname(apiEmployee.getFirstname());
        serviceEmployee.setTeamId(apiEmployee.getTeamId());
        return serviceEmployee;
    }
}
