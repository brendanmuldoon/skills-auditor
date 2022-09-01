package com.example.skillbc.skill.domain.skill.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryDTOTests {

    @Test
    void test01() {
        CategoryDTO categoryDTO1 = generateCategoryDto();
        CategoryDTO categoryDTO2 = new CategoryDTO(categoryDTO1);
        assertEquals(categoryDTO2, categoryDTO1);
    }

    private CategoryDTO generateCategoryDto() {
        return new CategoryDTO("1", "test");
    }

}