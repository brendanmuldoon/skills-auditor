package com.example.skillbc.skill.application.category.interfaces;

import com.example.skillbc.skill.domain.skill.Category;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;

public interface ICategoryToCategoryJpaMapper {
    CategoryJpaValueObject map(Category category);
}
