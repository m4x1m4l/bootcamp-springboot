package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiEmployeeMapper;
import com.max.bootcampspringboot.api.model.ApiEmployee;
import com.max.bootcampspringboot.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ApiEmployee getEmployee(@PathVariable @Min(value = 1, message = "{id.min}") int id){
        return ApiEmployeeMapper.toApiEmployee(employeeService.getEmployee(id));
    }

    @GetMapping
    List<ApiEmployee> getAllEmployees(){
        return ApiEmployeeMapper.toApiEmployee(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<ApiEmployee> addEmployee(@RequestBody @Valid ApiEmployee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiEmployeeMapper.toApiEmployee(employeeService.addEmployee(ApiEmployeeMapper.toServiceEmployee(employee))));
    }

    @PutMapping
    public ApiEmployee updateEmployee(@RequestBody @Valid ApiEmployee employee){
        return ApiEmployeeMapper.toApiEmployee(employeeService.updateEmployee(ApiEmployeeMapper.toServiceEmployee(employee)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable @Min(value = 1, message = "{id.min}") int id){
        ApiEmployee temp =ApiEmployeeMapper.toApiEmployee(employeeService.getEmployee(id));

        if (temp == null) throw new RuntimeException("Employee id not found");
        employeeService.deleteEmployee(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @GetMapping("/firstnames")
    public List<String> getFirstnamesByTeamName(@RequestParam(name = "teamName") String teamName){
        return employeeService.findFirstnameByTeamName(teamName);
    }

    @GetMapping("/per-team")
    public List<String> getEmployeeCountPerTeam(){
        return employeeService.findEmployeeCountPerTeam();
    }

    @GetMapping("/older-than/{age}")
    public List<ApiEmployee> getNamesOfEmployeesOlderThanX(@PathVariable int age){
        return ApiEmployeeMapper.toApiEmployee(employeeService.findNamesOfEmployeesOlderThanX(age));
    }
    @GetMapping("/sorted-by-skill-count")
    public List<ApiEmployee> getEmployeesSortedPerSkillCount(){
        //bad weil man skills nicht im apiEmployee drin hat?
        return ApiEmployeeMapper.toApiEmployee(employeeService.findEmployeesSortedPerSkillCount());
    }

    @GetMapping("/skill-sum")
    public List<String> getEmployeeSkillSum(){
        return employeeService.findEmployeeSkillSum();
    }


    @GetMapping("/skills-by-skill")
    public List<String> getEmployeeBySkillAndShowSkills(@RequestParam(name = "skillName")String skillName){
        return employeeService.findEmployeeBySkillAndShowSkills(skillName);
    }

    @GetMapping("/by-birth-year/{birthYear}")
    public List<String> getEmployeesByBirthYear(@PathVariable int birthYear){
        return employeeService.findEmployeesByBirthYear(birthYear);
    }

    @GetMapping("/get-employees-with-more-than-x-skills/{skillCount}")
    public List<String> getEmplfindNameAndSkillOfEmployeesWithMoreThanXSkillsoyeesByBirthYear(@PathVariable int skillCount){
        return employeeService.findNameAndSkillOfEmployeesWithMoreThanXSkills(skillCount);
    }


}

