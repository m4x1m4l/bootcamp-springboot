package com.max.bootcampspringboot.service;

import com.max.bootcampspringboot.data.entity.Employee;
import com.max.bootcampspringboot.data.entity.Knowledge;
import com.max.bootcampspringboot.data.entity.Team;
import com.max.bootcampspringboot.data.repository.EmployeeRepository;
import com.max.bootcampspringboot.data.repository.TeamRepository;
import com.max.bootcampspringboot.service.mapper.ServiceTeamMapper;
import com.max.bootcampspringboot.service.model.ServiceTeam;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public List<String> findTeamsWithMoreThanXEmployees(int employeeCount){
        return teamRepository.findTeamsWithMoreThanXEmployees(employeeCount);
    }

    public String findLargestTeam(){
        return teamRepository.findLargestTeam();
    }

    public List<String> findSmallestTeams(){
        //Liste mit Teams holen
        //Team , Size map machen
        //sortieren nach Size
        //schauen ob Size von [0] bei dem folgenden gleich ist
        //in return Liste mit Name, Size einfügen
        List<Team> teams = teamRepository.findAll();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        teams.forEach(t -> {
            map.put(t.getName(), t.getEmployees().size());
        });

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue());

        if (sortedEntries.isEmpty()) {
            System.out.println("Die Map ist leer.");
            return null;
        }

        int smallestTeamSize = sortedEntries.get(0).getValue();

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            if (entry.getValue() > smallestTeamSize) {
                break;
            }
            result.add(entry.getKey() + " " + entry.getValue());
        }

        return result;
    }

    public List<String> findTeamLeadAndTeamMemberCount(){
        List<Team> teams = teamRepository.findAll();
        List<String> result = new ArrayList<>();
        teams.forEach(team -> {
            result.add(team.getName() + ", " + team.getTeamLead().getFirstname() + " " + team.getTeamLead().getLastname() + ", " + team.getEmployees().size());
                }
        );
        return result;
    }

    public List<String> findEmployeesInTeamSameBirthdayMonthAsTeamLead(){

        List<String> result = new ArrayList<>();
        List<Team> teams = teamRepository.findAll();
        teams.forEach(team -> {
            LocalDate teamLeadBirthdate = team.getTeamLead().getBirthdate();
            team.getEmployees().forEach(employee -> {
                if (employee.getBirthdate().getMonth() == teamLeadBirthdate.getMonth() && team.getTeamLead().getId() != employee.getId()){
                    result.add(employee.getFirstname()
                            + " " + employee.getLastname() + ", " + team.getName() + ", " + team.getTeamLead().getFirstname()
                            + " " + team.getTeamLead().getLastname() + ", " + employee.getBirthdate() + ", " + team.getTeamLead().getBirthdate());
                }
            });
        });
        return result;
    }

    public List<String> findBestSkilledEmployeeInTeam(){
        //Teamname, Name, Skill, experiencelevel

        //welche Teams gibt es -> teamname
        //welche Skills sind in einem Team? team -> skills
        //Team, Skill -> höchste experience level
        //team, skill, höchste experience level -> employee
        List<String> result = new ArrayList<>();

        List<Team> teams = teamRepository.findAll();
        teams.forEach(team -> {
            List<Knowledge> teamKnowledges = new ArrayList<>();
            team.getEmployees().forEach(teamEmployee -> {

                teamEmployee.getKnowledges().forEach(
                        teamEmployeeKnowledges -> {
                            teamKnowledges.add(teamEmployeeKnowledges);
                        }
                );
            });

            teamKnowledges.sort(new Comparator<Knowledge>() {
                @Override
                public int compare(Knowledge k1, Knowledge k2) {
                    return Integer.compare(k2.getExperienceLevel(), k1.getExperienceLevel());
                }
            });
            ArrayList<String> foundSkills = new ArrayList<>();
            ArrayList<Knowledge> uniqueSkills = new ArrayList<>();
            teamKnowledges.forEach(knowledge -> {
                if(!foundSkills.contains(knowledge.getSkill().getName())){
                    uniqueSkills.add(knowledge);
                    foundSkills.add(knowledge.getSkill().getName());
                }

            });
            uniqueSkills.forEach(skill -> {
                result.add(team.getName() + ", " + skill.getEmployee().getFirstname() + " " + skill.getEmployee().getLastname() + ", " + skill.getSkill().getName() + ", " + skill.getExperienceLevel());
            });

        });

        return result;
    }
}
