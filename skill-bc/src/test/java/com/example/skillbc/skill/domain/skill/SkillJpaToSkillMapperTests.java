package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillJpaToSkillMapperTests {

    private SkillJpaToSkillMapper classUnderTest = new SkillJpaToSkillMapper();

    @Test
    void test01() {
        SkillJpa skillJpa = generateSkillJpa();
        Skill response = classUnderTest.map(skillJpa);
        assertEquals(response.id(), response.id());
        assertEquals(response.category(), skillJpa.getCategory().getId());
        assertEquals(response.description(), skillJpa.getDescription());
    }

    private SkillJpa generateSkillJpa() {
        return new SkillJpa("1", "test", CategoryJpaValueObject.categoryJpaOf("2", "cat-test"));
    }

}