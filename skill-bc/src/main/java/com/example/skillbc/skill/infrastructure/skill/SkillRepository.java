package com.example.skillbc.skill.infrastructure.skill;

import com.example.skillbc.skill.application.skill.interfaces.ISkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SkillRepository implements ISkillRepository {

    private CrudSkillRepository repository;

    @Override
    public Iterable<SkillJpa> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SkillJpa> findById(String skillId) {
        return repository.findById(skillId);
    }

    @Override
    public List<SkillJpa> findByCategoryId(String categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    @Override
    public void save(SkillJpa skillJpa) {
        repository.save(skillJpa);
    }

    @Override
    public void delete(SkillJpa skillJpa) {
        repository.delete(skillJpa);
    }

    @Override
    public Optional<SkillJpa> findByDescription(String description) {
        return repository.findByDescription(description);
    }
}
