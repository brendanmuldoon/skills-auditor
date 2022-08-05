package com.example.skillbc.skill.domain.skill.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SkillDTOList {

    private List<SkillDTO> skills;

    public SkillDTOList() {
        this.skills = new ArrayList<>();
    }
}
