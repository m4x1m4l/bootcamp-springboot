package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiSkillMapper;
import com.max.bootcampspringboot.api.model.ApiSkill;
import com.max.bootcampspringboot.service.SkillService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/skills")
@Validated
public class SkillController {
    private final SkillService skillService;
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{id}")
    public ApiSkill getSkill(@PathVariable @Min(value = 1, message = "{id.min}")int id){
        return ApiSkillMapper.toApiSkill(skillService.getSkill(id));
    }

    @GetMapping
    List<ApiSkill> getAllSkills(){
        return ApiSkillMapper.toApiSkill(skillService.getAllSkills());
    }

    @PostMapping
    public ResponseEntity<ApiSkill> addSkill( @RequestBody ApiSkill skill){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiSkillMapper.toApiSkill(skillService.addSkill(ApiSkillMapper.toServiceSkill(skill))));
    }

    @PutMapping
    public ApiSkill updateSkill(@RequestBody ApiSkill skill){
        return ApiSkillMapper.toApiSkill(skillService.updateSkill(ApiSkillMapper.toServiceSkill(skill)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable @Min(value = 1, message = "{id.min}") int id){
        ApiSkill temp = ApiSkillMapper.toApiSkill(skillService.getSkill(id));

        if (temp == null) throw new RuntimeException("Skill id not found");
        skillService.deleteSkill(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");

    }

    @GetMapping("/rarest-skill")
    public String getRarestSkill(){
        return skillService.findRarestSkill();
    }

    @GetMapping("/avg-skill")
    public List<String> getAverageSkillPerSkill(){
        return skillService.findAverageSkillPerSkill();
    }

}
