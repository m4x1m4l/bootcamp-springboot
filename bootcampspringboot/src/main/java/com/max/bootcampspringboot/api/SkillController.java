package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiSkillMapper;
import com.max.bootcampspringboot.api.model.ApiSkill;
import com.max.bootcampspringboot.data.entity.Skill;
import com.max.bootcampspringboot.service.SkillService;
import com.max.bootcampspringboot.service.mapper.ServiceSkillMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;
    private final ApiSkillMapper apiSkillMapper = new ApiSkillMapper();
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{id}")
    public ApiSkill getSkill(@PathVariable int id){
        return apiSkillMapper.toApiSkill(skillService.getSkill(id));
    }

    @GetMapping
    List<ApiSkill> getAllSkills(){
        return apiSkillMapper.toApiSkill(skillService.getAllSkills());
    }

    @PostMapping
    public ApiSkill addSkill(@RequestBody ApiSkill skill){
        return apiSkillMapper.toApiSkill(skillService.addSkill(apiSkillMapper.toServiceSkill(skill)));
    }

    @PutMapping
    public ApiSkill updateSkill(@RequestBody ApiSkill skill){
        return apiSkillMapper.toApiSkill(skillService.updateSkill(apiSkillMapper.toServiceSkill(skill)));
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable int id){
        skillService.deleteSkill(id);
    }

}
