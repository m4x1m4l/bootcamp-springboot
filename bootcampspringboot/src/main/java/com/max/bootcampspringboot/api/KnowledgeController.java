package com.max.bootcampspringboot.api;

import com.max.bootcampspringboot.api.mapper.ApiKnowledgeMapper;
import com.max.bootcampspringboot.api.model.ApiKnowledge;
import com.max.bootcampspringboot.service.KnowledgeService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ApiKnowledge getKnowledge(@PathVariable @Min(value = 1, message = "{id.min}") int employeeId, @Min(value = 1, message = "{id.min}") @PathVariable int skillId){
        return ApiKnowledgeMapper.toApiKnowledge(knowledgeService.getKnowledge(employeeId, skillId));
    }

    @GetMapping("/{employeeId}")
    List<ApiKnowledge> getAllByEmployeeId(@PathVariable @Min(value = 1, message = "{id.min}") int employeeId){
        return ApiKnowledgeMapper.toApiKnowledge(knowledgeService.getAllKnowledgesByEmployeeId(employeeId));
    }

    @PostMapping
    public ResponseEntity<ApiKnowledge> addKnowledgeByEmployeeId(@RequestBody ApiKnowledge knowledge){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiKnowledgeMapper.
                toApiKnowledge(knowledgeService.addKnowledge(ApiKnowledgeMapper.toServiceKnowledge(knowledge))));
    }

    @PutMapping
    public ApiKnowledge updateKnowledge(@RequestBody ApiKnowledge knowledge){
        return ApiKnowledgeMapper.toApiKnowledge(knowledgeService.updateKnowledge(ApiKnowledgeMapper.toServiceKnowledge(knowledge)));
    }

    @DeleteMapping("/{employeeId}/{skillId}")
    public ResponseEntity<String> deleteKnowledge(@PathVariable @Min(value = 1, message = "{id.min}")int employeeId, @Min(value = 1, message = "{id.min}")@PathVariable int skillId){
        ApiKnowledge temp = ApiKnowledgeMapper.toApiKnowledge(knowledgeService.getKnowledge(employeeId, skillId));

        if (temp == null) throw new RuntimeException("Employee id not found");
        knowledgeService.deleteKnowledge(employeeId, skillId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");

    }
}
