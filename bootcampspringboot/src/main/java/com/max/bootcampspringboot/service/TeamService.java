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
        Employee teamlead = employeeRepository.findById(serviceTeam.getTeamleadId())
                .orElseThrow(
                        () ->  new RuntimeException("employee id (teamLead id) not found: " + serviceTeam.getTeamleadId()));

        Team teamToSave = ServiceTeamMapper.toTeam(serviceTeam);
        teamToSave.setTeamLead(teamlead);

        return ServiceTeamMapper.toServiceTeam(teamRepository.save(teamToSave));
    }

    public ServiceTeam updateTeam(ServiceTeam serviceTeam) {
        Team toSave = this.teamRepository.findById(serviceTeam.getId()).orElseThrow(() -> new RuntimeException("Team ID not found: " + serviceTeam.getId()));
        toSave.setName(serviceTeam.getName());
        //set Teamleader
        Employee teamlead = employeeRepository.findById(serviceTeam.getTeamleadId()).orElseThrow(
                () ->  new RuntimeException("employee id (teamlead id) not found: " + serviceTeam.getTeamleadId()));
        toSave.setTeamLead(teamlead);

        return ServiceTeamMapper.toServiceTeam(teamRepository.save(toSave));
    }

    public void deleteTeam(int id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
        }
    }
}
