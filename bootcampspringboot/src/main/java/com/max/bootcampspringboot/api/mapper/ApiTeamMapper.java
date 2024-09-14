package com.max.bootcampspringboot.api.mapper;

import com.max.bootcampspringboot.api.model.ApiTeam;
import com.max.bootcampspringboot.service.model.ServiceTeam;

import java.util.List;

public class ApiTeamMapper {

    public static ApiTeam toApiTeam(ServiceTeam serviceTeam){
        ApiTeam apiTeam = new ApiTeam();
        apiTeam.setId(serviceTeam.getId());
        apiTeam.setName(serviceTeam.getName());
        apiTeam.setTeamleadId(serviceTeam.getTeamlead().getId());
        return apiTeam;
    }

    public static List<ApiTeam> toApiTeam(List<ServiceTeam> serviceTeam){
        return serviceTeam.stream().map(ApiTeamMapper::toApiTeam).toList();
    }

    public static ServiceTeam toServiceTeam(ApiTeam apiTeam){
        ServiceTeam serviceTeam = new ServiceTeam();
        serviceTeam.setId(apiTeam.getId());
        serviceTeam.setName(apiTeam.getName());
        //serviceTeam.setTeamleadId(apiTeam.getTeamleadId());
        //teamleadid not set
        //gets set in add and update method in TeamService
        return serviceTeam;
    }
}
