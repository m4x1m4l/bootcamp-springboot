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
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ApiEmployee getEmployee(@PathVariable int id){
        return ApiEmployeeMapper.toApiEmployee(employeeService.getEmployee(id));
    }

    @GetMapping("")
    public List<ApiEmployee> getAllEmployees(){
        return ApiEmployeeMapper.toApiEmployee(employeeService.getAllEmployees());
    }

    @PostMapping
    public ApiEmployee addEmployee(@RequestBody ApiEmployee employee) {
        return ApiEmployeeMapper.toApiEmployee(employeeService.addEmployee(ApiEmployeeMapper.toServiceEmployee(employee)));
    }

    @PutMapping("/{employeeId}")
    public ApiEmployee updateEmployee(@PathVariable int employeeId, @RequestBody ApiEmployee employee){
        return ApiEmployeeMapper.toApiEmployee(employeeService.updateEmployee(employeeId, ApiEmployeeMapper.toServiceEmployee(employee)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}

