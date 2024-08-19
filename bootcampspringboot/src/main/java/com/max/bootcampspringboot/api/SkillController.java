package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiSkillMapper;
import com.max.bootcampspringboot.api.model.ApiSkill;
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
    public ApiSkill getSkill(@PathVariable int id){
        return ApiSkillMapper.toApiSkill(skillService.getSkill(id));
    }

    @GetMapping
    List<ApiSkill> getAllSkills(){
        return ApiSkillMapper.toApiSkill(skillService.getAllSkills());
    }

    @PostMapping
    public ApiSkill addSkill(@RequestBody ApiSkill skill){
        return ApiSkillMapper.toApiSkill(skillService.addSkill(ApiSkillMapper.toServiceSkill(skill)));
    }

    @PutMapping
    public ApiSkill updateSkill(@RequestBody ApiSkill skill){
        return ApiSkillMapper.toApiSkill(skillService.updateSkill(ApiSkillMapper.toServiceSkill(skill)));
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable int id){
        skillService.deleteSkill(id);
    }

    @GetMapping("/rarest-skill")
    public String getRarestSkill(){
        return skillService.findRarestSkill();
    }

}
