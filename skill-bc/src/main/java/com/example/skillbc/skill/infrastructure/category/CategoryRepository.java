package com.example.skillbc.skill.infrastructure.category;

import com.example.skillbc.skill.application.category.interfaces.ICategoryRepository;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryRepository implements ICategoryRepository {

    private CrudCategoryRepository repository;

    @Override
    public Optional<CategoryJpaValueObject> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void save(CategoryJpaValueObject categoryJpaValueObject) {
        repository.save(categoryJpaValueObject);
    }

    @Override
    public Optional<CategoryJpaValueObject> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    @Override
    public void delete(CategoryJpaValueObject categoryJpa) {
        repository.delete(categoryJpa);
    }
}
