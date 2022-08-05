package com.example.skillbc.skill.application.skill.interfaces;

import com.example.skillbc.skill.infrastructure.skill.SkillJpa;

import java.util.List;
import java.util.Optional;

public interface ISkillRepository {
    Iterable<SkillJpa> findAll();

    Optional<SkillJpa> findById(String skillId);

    List<SkillJpa> findByCategoryId(String categoryId);

    void save(SkillJpa skillJpa);

    void delete(SkillJpa skillJpa);

    Optional<SkillJpa> findByDescription(String description);
}
