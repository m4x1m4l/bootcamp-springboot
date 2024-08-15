package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiEmployeeMapper;
import com.max.bootcampspringboot.api.model.ApiEmployee;
import com.max.bootcampspringboot.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ApiEmployeeMapper apiEmployeeMapper = new ApiEmployeeMapper();
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ApiEmployee getEmployee(@PathVariable int id){
        return apiEmployeeMapper.toApiEmployee(employeeService.getEmployee(id));
    }

    @GetMapping
    List<ApiEmployee> getAllEmployees(){
        return apiEmployeeMapper.toApiEmployee(employeeService.getAllEmployees());
    }

    @PostMapping
    public ApiEmployee addEmployee(@RequestBody ApiEmployee employee){
        return apiEmployeeMapper.toApiEmployee(employeeService.addEmployee(apiEmployeeMapper.toServiceEmployee(employee)));
    }

    @PutMapping
    public ApiEmployee updateEmployee(@RequestBody ApiEmployee employee){
        return apiEmployeeMapper.toApiEmployee(employeeService.updateEmployee(apiEmployeeMapper.toServiceEmployee(employee)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}

