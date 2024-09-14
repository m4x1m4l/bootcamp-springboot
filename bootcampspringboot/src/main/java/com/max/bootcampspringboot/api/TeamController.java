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

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ApiTeam getTeam(@PathVariable int id){
        return ApiTeamMapper.toApiTeam(teamService.getTeam(id));
    }

    @GetMapping
    public List<ApiTeam> getAllTeams(){
        return ApiTeamMapper.toApiTeam(teamService.getAllTeams());
    }

    @PostMapping
    public ApiTeam addTeam(@RequestBody ApiTeam team){
        return ApiTeamMapper.toApiTeam(teamService.addTeam(ApiTeamMapper.toServiceTeam(team)));
    }

    @PutMapping("/{teamId}")
    public ApiTeam updateTeam(@PathVariable int teamId, @RequestBody ApiTeam team){
        return ApiTeamMapper.toApiTeam(teamService.updateTeam(teamId, ApiTeamMapper.toServiceTeam(team)));
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable int id){
        teamService.deleteTeam(id);
    }

}
