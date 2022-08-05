package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.application.category.interfaces.ICategoryToCategoryJpaMapper;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryJpaMapper implements ICategoryToCategoryJpaMapper {

    @Override
    public CategoryJpaValueObject map(Category category) {
        return new CategoryJpaValueObject(category.id().id(), category.description());
    }
}
