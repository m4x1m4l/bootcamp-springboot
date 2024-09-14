package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiKnowledgeMapper;
import com.max.bootcampspringboot.api.model.ApiKnowledge;
import com.max.bootcampspringboot.service.KnowledgeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/knowledges")
public class KnowledgeController {
    private final KnowledgeService knowledgeService;

    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/{employeeId}/{skillId}")
    public ApiKnowledge getKnowledge(@PathVariable int employeeId, @PathVariable int skillId){
        return ApiKnowledgeMapper.toApiKnowledge(knowledgeService.getKnowledge(employeeId, skillId));
    }

    @GetMapping("/{employeeId}")
    public List<ApiKnowledge> getAllByEmployeeId(@PathVariable int employeeId){
        return ApiKnowledgeMapper.toApiKnowledge(knowledgeService.getAllKnowledgesByEmployeeId(employeeId));
    }

    @PostMapping
    public ApiKnowledge addKnowledgeByEmployeeId(@RequestBody ApiKnowledge knowledge){
        return ApiKnowledgeMapper.toApiKnowledge(knowledgeService.addKnowledge(ApiKnowledgeMapper.toServiceKnowledge(knowledge)));
    }

    @PutMapping("/{employeeId}/{skillId}")
    public ApiKnowledge updateKnowledge(@PathVariable int skillId, @PathVariable int employeeId, @RequestBody ApiKnowledge knowledge){
        return ApiKnowledgeMapper.toApiKnowledge(knowledgeService.updateKnowledge(skillId, employeeId, ApiKnowledgeMapper.toServiceKnowledge(knowledge)));
    }

    @DeleteMapping("/{employeeId}/{skillId}")
    public void deleteKnowledge(@PathVariable int employeeId, @PathVariable int skillId){
        knowledgeService.deleteSkill(employeeId, skillId);
    }
}
