package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.domain.common.Identity;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillToSkillJpaMapperTests {

    private final SkillToSkillJpaMapper classUnderTest = new SkillToSkillJpaMapper();

    @Test
    void test01() {
        Skill skill = generateSkill();
        CategoryJpaValueObject category = generateCategoryJpa();
        SkillJpa response = classUnderTest.map(skill, category);

        assertEquals("1", response.getId());
        assertEquals("test", response.getDescription());
        assertEquals("1", response.getCategory().getId());
        assertEquals("test", response.getCategory().getDescription());
    }

    private CategoryJpaValueObject generateCategoryJpa() {
        return CategoryJpaValueObject.categoryJpaOf("1", "test");
    }


    private Skill generateSkill() {
        return new Skill(new Identity("1"), "test", "1");
    }

}