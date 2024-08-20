package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.Employee;
import com.max.bootcampspringboot.data.entity.Knowledge;
import com.max.bootcampspringboot.data.entity.Team;
import com.max.bootcampspringboot.data.repository.EmployeeRepository;
import com.max.bootcampspringboot.data.repository.KnowledgeRepository;
import com.max.bootcampspringboot.data.repository.TeamRepository;
import com.max.bootcampspringboot.service.mapper.ServiceEmployeeMapper;
import com.max.bootcampspringboot.service.model.ServiceEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final KnowledgeRepository knowledgeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository, KnowledgeRepository knowledgeRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
        this.knowledgeRepository = knowledgeRepository;
    }

    public ServiceEmployee getEmployee(int id) {
        return ServiceEmployeeMapper.toServiceEmployee(this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)));
    }

    public List<ServiceEmployee> getAllEmployees() {
        return ServiceEmployeeMapper.toServiceEmployee( this.employeeRepository.findAll());
    }

    public ServiceEmployee addEmployee(ServiceEmployee employee) {

        Employee employeeToSave = ServiceEmployeeMapper.toEmployee(employee);
        //add Team
        Team teamOfEmployee = teamRepository.findById(employee.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team ID of Employee not found: " + employee.getTeamId()));
        employeeToSave.setTeam(teamOfEmployee);

        return ServiceEmployeeMapper.toServiceEmployee( this.employeeRepository.save(employeeToSave));
    }

    public ServiceEmployee updateEmployee(ServiceEmployee employee) {
        Employee employeeToSave = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new RuntimeException("Employee ID not found: " + employee.getId()));

        employeeToSave.setFirstname(employee.getFirstname());
        employeeToSave.setLastname(employee.getLastname());
        employeeToSave.setBirthdate(employee.getBirthdate());
        employeeToSave.setSalutation(employee.getSalutation());

        //add Team
        Team teamOfEmployee = teamRepository.findById(employee.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team ID of Employee not found: " + employee.getTeamId()));

        employeeToSave.setTeam(teamOfEmployee);

        return ServiceEmployeeMapper.toServiceEmployee(employeeRepository.save(employeeToSave));

    }

    public void deleteEmployee(int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }
    }

    public List<String> findFirstnameByTeamName(String teamName){
        return employeeRepository.findFirstnameByTeamName(teamName);
    }

    public List<String> findEmployeeCountPerTeam(){
        return employeeRepository.findEmployeeCountPerTeam();
    }

    public List<ServiceEmployee> findNamesOfEmployeesOlderThanX(int age){
        List<ServiceEmployee> listEmployees = getAllEmployees();
        return listEmployees.stream()
                .filter(e -> {
                    LocalDate birthdate = e.getBirthdate();
                    if (birthdate == null) {
                        return false;
                    }
                    int employeeAge = Period.between(birthdate, LocalDate.now()).getYears();
                    return employeeAge >= age;
                })
                .toList();

    }

    public List<ServiceEmployee> findEmployeesSortedPerSkillCount(){
        return ServiceEmployeeMapper.toServiceEmployee(employeeRepository.findEmployeesSortedPerSkillCount());
    }

    public List<String> findEmployeeSkillSum(){
        return employeeRepository.findEmployeeSkillSum();
    }

    public List<String> findEmployeeBySkillAndShowSkills(String skillName){
        List<Employee> employeesWithSkill = employeeRepository.findEmployeesBySkillName(skillName);
        ArrayList<String> result = new ArrayList<>();

        employeesWithSkill.forEach(employee -> {
            List<Knowledge> knowledges = knowledgeRepository.findByEmployeeId(employee.getId());
            StringBuilder sb = new StringBuilder();

            sb.append(employee.getFirstname() + " " + employee.getLastname() + " (");
            for (Knowledge k : knowledges){
                sb.append(k.getSkill().getName() + ", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(")");

            result.add(sb.toString());
        });

        return result;
    }

    public List<String> findEmployeesByBirthYear(int birthYear){

        List<Employee> employees = employeeRepository.findAll();
        employees = employees.stream().filter(employee ->
            employee.getBirthdate().getYear() == birthYear
        ).toList();
        List<String> result = new ArrayList<>();
        employees.forEach(employee -> {
            result.add(employee.getFirstname() + " " + employee.getLastname() + " (" + employee.getBirthdate() + ")");
        });
        return result;
    }
}
