package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiTeamMapper;
import com.max.bootcampspringboot.api.model.ApiTeam;
import com.max.bootcampspringboot.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final ApiTeamMapper apiTeamMapper = new ApiTeamMapper();

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ApiTeam getTeam(@PathVariable int id){
        return apiTeamMapper.toApiTeam(teamService.getTeam(id));
    }

    @GetMapping
    List<ApiTeam> getAllTeams(){
        return apiTeamMapper.toApiTeam(teamService.getAllTeams());
    }

    @PostMapping
    public ApiTeam addTeam(@RequestBody ApiTeam team){
        return apiTeamMapper.toApiTeam(teamService.addTeam(apiTeamMapper.toServiceTeam(team)));
    }

    @PutMapping
    public ApiTeam updateTeam(@RequestBody ApiTeam team){
        return apiTeamMapper.toApiTeam(teamService.updateTeam(apiTeamMapper.toServiceTeam(team)));
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable int id){
        teamService.deleteTeam(id);
    }

}
