package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.repository.SkillRepository;
import com.max.bootcampspringboot.data.repository.TeamRepository;
import com.max.bootcampspringboot.service.mapper.ServiceTeamMapper;
import com.max.bootcampspringboot.service.model.ServiceSkill;
import com.max.bootcampspringboot.service.model.ServiceTeam;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final ServiceTeamMapper serviceTeamMapper = new ServiceTeamMapper();

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public ServiceTeam getTeam(int id) {
        return serviceTeamMapper.toServiceTeam(this.teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id)));
    }

    public List<ServiceTeam> getAllTeams() {
        return serviceTeamMapper.toServiceTeam( this.teamRepository.findAll());
    }

    public ServiceTeam addTeam(ServiceTeam serviceTeam) {
        return serviceTeamMapper.toServiceTeam(teamRepository.save(serviceTeamMapper.toTeam(serviceTeam)));
    }

    public ServiceTeam updateTeam(ServiceTeam serviceTeam) {
        ServiceTeam toUpdate = getTeam(serviceTeam.getId());
        toUpdate.setName(serviceTeam.getName());
        return serviceTeamMapper.toServiceTeam(teamRepository.save(serviceTeamMapper.toTeam(toUpdate)));
    }

    public void deleteTeam(int id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
        }
    }
}
