package com.example.skillbc.skill.application.skill.interfaces;

import com.example.skillbc.skill.domain.skill.Skill;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;

public interface ISkillToSkillJpaMapper {
    SkillJpa map(Skill skill, CategoryJpaValueObject category);
}
