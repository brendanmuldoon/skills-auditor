package com.example.skillbc.skill.ui.skill;

import com.example.skillbc.skill.domain.skill.DTO.SkillDTOList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/skill")
@AllArgsConstructor
public class SkillController {

    private ISkillQueryHandler queryHandler;


    @GetMapping("/findAll")
    public Iterable<?> findAllSkills() {
        return queryHandler.findAll();
    }

    @GetMapping("/{skill_id}")
    public Optional<?> getSkillById(@PathVariable(name = "skill_id") String skillId) {
        Optional<?> response = queryHandler.findBySkillId(skillId);
        if(response.isPresent()) {
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Skill id: '%s' not found", skillId));
        }
    }

    // view all skills by category
    @GetMapping("/findAllSkillsByCategory/{category_id}")
    public SkillDTOList getSkillsByCategoryId(@PathVariable(name = "category_id") String categoryId){
        SkillDTOList response = queryHandler.findByCategoryId(categoryId);
        if(!response.getSkills().isEmpty()) {
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Skill id: '%s' not found", categoryId));
        }
    }


}
