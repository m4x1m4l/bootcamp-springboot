package com.max.bootcampspringboot.service.mapper;

import com.max.bootcampspringboot.api.model.ApiTeam;
import com.max.bootcampspringboot.data.entity.Skill;
import com.max.bootcampspringboot.data.entity.Team;
import com.max.bootcampspringboot.service.model.ServiceTeam;

import java.util.List;

public class ServiceTeamMapper {
    public static ServiceTeam toServiceTeam(Team team) {
        ServiceTeam serviceTeam = new ServiceTeam();
        serviceTeam.setId(team.getId());
        serviceTeam.setName(team.getName());
        serviceTeam.setTeamleadId(team.getTeamLead().getId());
        return serviceTeam;
    }

    public static List<ServiceTeam> toServiceTeam(List<Team> teams){
        return teams.stream().map(ServiceTeamMapper::toServiceTeam).toList();
    }

    public static Team toTeam(ServiceTeam serviceTeam){
        Team team = new Team();
        team.setId(serviceTeam.getId());
        team.setName(serviceTeam.getName());
        //teamleadid not set
        return team;
    }


}
