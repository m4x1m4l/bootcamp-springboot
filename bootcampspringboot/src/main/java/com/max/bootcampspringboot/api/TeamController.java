package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiTeamMapper;
import com.max.bootcampspringboot.api.model.ApiTeam;
import com.max.bootcampspringboot.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    List<ApiTeam> getAllTeams(){
        return ApiTeamMapper.toApiTeam(teamService.getAllTeams());
    }

    @PostMapping
    public ResponseEntity<ApiTeam> addTeam(@RequestBody ApiTeam team){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiTeamMapper.toApiTeam(teamService.addTeam(ApiTeamMapper.toServiceTeam(team))));
    }

    @PutMapping
    public ApiTeam updateTeam(@RequestBody ApiTeam team){
        return ApiTeamMapper.toApiTeam(teamService.updateTeam(ApiTeamMapper.toServiceTeam(team)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable int id){
        ApiTeam temp = ApiTeamMapper.toApiTeam(teamService.getTeam(id));

        if (temp == null) throw new RuntimeException("Team id not found");
        teamService.deleteTeam(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");

    }

    @GetMapping("/with-more-than-employees/{employeeCount}")
    public List<String> getTeamsWithMoreThanXEmployees(@PathVariable int employeeCount){
        return teamService.findTeamsWithMoreThanXEmployees(employeeCount);
    }

    @GetMapping("/largest-team")
    public String getLargestTeam(){
        return teamService.findLargestTeam();
    }

    @GetMapping("/smallest-teams")
    public List<String> getSmallestTeam(){
        return teamService.findSmallestTeams();
    }

    @GetMapping("/team-lead-member-count")
    public List<String> getTeamLeadAndTeamMemberCount(){
        return teamService.findTeamLeadAndTeamMemberCount();
    }

    @GetMapping("/employees-in-team-same-birthday-month-as-teamlead")
    public List<String> getEmployeesInTeamSameBirthdayMonthAsTeamLead(){
        return teamService.findEmployeesInTeamSameBirthdayMonthAsTeamLead();
    }

    @GetMapping("/test")
    public List<String> getBestSkilledEmployeeInTeam(){
        return teamService.findBestSkilledEmployeeInTeam();
    }
}
