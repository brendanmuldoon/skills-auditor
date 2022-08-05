package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.application.skill.interfaces.ISkillToSkillJpaMapper;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;
import org.springframework.stereotype.Component;

@Component
public class SkillToSkillJpaMapper implements ISkillToSkillJpaMapper {

    @Override
    public SkillJpa map(Skill skill, CategoryJpaValueObject category) {

        CategoryJpaValueObject categoryJpaValueObject = CategoryJpaValueObject.categoryJpaOf(category.getId(), category.getDescription());

        return SkillJpa.skillJpaOf(
                skill.id().id(),
                skill.description(),
                categoryJpaValueObject);

    }
}
