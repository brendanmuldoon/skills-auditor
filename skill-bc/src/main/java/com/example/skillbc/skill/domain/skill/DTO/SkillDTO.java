package com.example.skillbc.skill.domain.skill.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class SkillDTO {

    private String id;
    private String description;
    private CategoryDTO category;

    public SkillDTO(){}

    public SkillDTO(SkillDTO skillDTO) {
        this(skillDTO.getId(), skillDTO.getDescription(), skillDTO.getCategory());
    }

    public SkillDTO(String id,
                    String description,
                    CategoryDTO category) {
        this.id = id;
        this.description=description;
        this.category=category;
    }
}
