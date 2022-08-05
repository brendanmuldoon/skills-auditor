package com.example.skillbc.skill.ui.skill;

import com.example.skillbc.skill.domain.skill.DTO.SkillDTO;
import com.example.skillbc.skill.domain.skill.DTO.SkillDTOList;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;

import java.util.Optional;

public interface ISkillQueryHandler {

    Iterable<SkillJpa> findAll();

    Optional<SkillDTO> findBySkillId(String skillId);

    SkillDTOList findByCategoryId(String categoryId);
}
