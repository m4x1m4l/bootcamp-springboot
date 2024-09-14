package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.Employee;
import com.max.bootcampspringboot.data.entity.Team;
import com.max.bootcampspringboot.data.repository.EmployeeRepository;
import com.max.bootcampspringboot.data.repository.TeamRepository;
import com.max.bootcampspringboot.service.mapper.ServiceTeamMapper;
import com.max.bootcampspringboot.service.model.ServiceTeam;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    public TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    public ServiceTeam getTeam(int id) {
        return ServiceTeamMapper.toServiceTeam(this.teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)));
    }

    public List<ServiceTeam> getAllTeams() {
        return ServiceTeamMapper.toServiceTeam( this.teamRepository.findAll());
    }

    public ServiceTeam addTeam(ServiceTeam serviceTeam) {
        Employee teamlead = employeeRepository.findById(serviceTeam.getTeamlead().getId())
                .orElseThrow(
                        () ->  new EntityNotFoundException("employee id (teamLead id) not found: " + serviceTeam.getTeamlead().getId()));

        Team teamToSave = ServiceTeamMapper.toTeam(serviceTeam);
        teamToSave.setTeamLead(teamlead);

        return ServiceTeamMapper.toServiceTeam(teamRepository.save(teamToSave));
    }

    public ServiceTeam updateTeam(int teamId, ServiceTeam serviceTeam) {
        if(teamId != serviceTeam.getId()) throw new RuntimeException("Id and Id in Object do not equal!");
        Team toSave = this.teamRepository.findById(serviceTeam.getId()).orElseThrow(() -> new RuntimeException("Team ID not found: " + serviceTeam.getId()));
        toSave.setName(serviceTeam.getName());
        //set Teamleader
        Employee teamlead = employeeRepository.findById(serviceTeam.getTeamlead().getId()).orElseThrow(
                () ->  new EntityNotFoundException("employee id (teamlead id) not found: " + serviceTeam.getTeamlead().getId()));
        toSave.setTeamLead(teamlead);

        return ServiceTeamMapper.toServiceTeam(teamRepository.save(toSave));
    }

    public void deleteTeam(int id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
        }
    }

    public List<String> findTeamsWithMoreThanXEmployees(int employeeCount){
        return teamRepository.findTeamsWithMoreThanXEmployees(employeeCount);
    }
}
