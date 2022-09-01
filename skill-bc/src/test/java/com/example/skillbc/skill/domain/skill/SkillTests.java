package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.domain.common.Identity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTests {

    @Test
    void test01() {
        Skill skill1 = generateSkill();
        Skill skill2 = new Skill(skill1);
        assertEquals(skill1, skill2);
    }

    @Test
    void tes02() {
        Skill skill = generateSkill();
        skill.update("test2");
        assertEquals("test2", skill.description());
    }

    private Skill generateSkill() {
        return new Skill(new Identity("1"), "test", "1");
    }

}