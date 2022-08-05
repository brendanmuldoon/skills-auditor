package com.example.skillbc.skill.infrastructure.skill;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudSkillRepository extends CrudRepository<SkillJpa, String> {
    List<SkillJpa> findAllByCategoryId(String categoryId);


    Optional<SkillJpa> findByDescription(String description);
}
