package com.example.skillbc.skill.application.category.interfaces;

import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;

import java.util.Optional;

public interface ICategoryRepository {

    Optional<CategoryJpaValueObject> findById(String id);

    void save(CategoryJpaValueObject categoryJpaValueObject);

    Optional<CategoryJpaValueObject> findByDescription(String description);

    void delete(CategoryJpaValueObject categoryJpa);
}
