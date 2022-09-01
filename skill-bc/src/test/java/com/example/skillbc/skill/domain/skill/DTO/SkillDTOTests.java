package com.example.skillbc.skill.domain.skill.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillDTOTests {

    @Test
    void test01() {
        SkillDTO skillDTO1 = generateSkillDto();
        SkillDTO skillDTO2 = new SkillDTO(skillDTO1);
        assertEquals(skillDTO1, skillDTO2);
    }

    private SkillDTO generateSkillDto() {
        return new SkillDTO("1", "test", new CategoryDTO("1", "test"));
    }

}