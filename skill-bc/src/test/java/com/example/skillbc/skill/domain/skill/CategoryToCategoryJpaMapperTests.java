package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.domain.common.Identity;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryToCategoryJpaMapperTests {

    private final CategoryToCategoryJpaMapper classUnderTest = new CategoryToCategoryJpaMapper();

    @Test
    void test01() {
        Category category = Category.categoryOf(new Identity("1"), "test");
        CategoryJpaValueObject response = classUnderTest.map(category);
        assertEquals(response.getDescription(), category.description());
        assertEquals(response.getId(), category.id().id());
    }

}