package com.example.skillbc.skill.domain.skill.interfaces;

import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;

public interface ISkillJpa {

    String getId();

    void setId(String id);

    String getDescription();

    void setDescription(String description);

    CategoryJpaValueObject getCategory();

    void setCategory(CategoryJpaValueObject category);
}
