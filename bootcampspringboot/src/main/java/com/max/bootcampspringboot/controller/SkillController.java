package com.max.bootcampspringboot.controller;

import com.max.bootcampspringboot.entity.Skill;
import com.max.bootcampspringboot.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable int id){
        return skillService.getSkill(id);
    }

    @GetMapping
    List<Skill> getAllSkills(){
        return skillService.getAllSkills();
    }

    @PostMapping
    public Skill addSkill(@RequestBody Skill skill){
        return skillService.addSkill(skill);
    }

    @PutMapping
    public Skill updateSkill(@RequestBody Skill skill){
        return skillService.updateSkill(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable int id){
        skillService.deleteSkill(id);
    }

}
