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
    private final ApiKnowledgeMapper apiKnowledgeMapper = new ApiKnowledgeMapper();

    public KnowledgeController(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/{employeeId}/{skillId}")
    public ApiKnowledge getKnowledge(@PathVariable int employeeId, @PathVariable int skillId){
        return apiKnowledgeMapper.toApiKnowledge(knowledgeService.getKnowledge(employeeId, skillId));
    }

    @GetMapping
    List<ApiKnowledge> getAllKnowledges(){
        return apiKnowledgeMapper.toApiKnowledge(knowledgeService.getAllKnowledges());
    }

    @PostMapping
    public ApiKnowledge addKnowledge(@RequestBody ApiKnowledge knowledge){
        return apiKnowledgeMapper.toApiKnowledge(knowledgeService.addKnowledge(apiKnowledgeMapper.toServiceKnowledge(knowledge)));
    }

    @PutMapping
    public ApiKnowledge updateKnowledge(@RequestBody ApiKnowledge knowledge){
        return apiKnowledgeMapper.toApiKnowledge(knowledgeService.updateKnowledge(apiKnowledgeMapper.toServiceKnowledge(knowledge)));
    }

    @DeleteMapping("/{employeeId}/{skillId}")
    public void deleteKnowledge(@PathVariable int employeeId, @PathVariable int skillId){
        knowledgeService.deleteSkill(employeeId, skillId);
    }
}
