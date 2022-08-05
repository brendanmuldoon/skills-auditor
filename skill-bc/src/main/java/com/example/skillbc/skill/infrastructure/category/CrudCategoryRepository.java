package com.example.skillbc.skill.infrastructure.category;

import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudCategoryRepository extends CrudRepository<CategoryJpaValueObject, String> {
    Optional<CategoryJpaValueObject> findByDescription(String description);
}
