package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.domain.common.Identity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTests {

    @Test
    void test01() {
        Category category1 = generateCategory();
        Category category2 = new Category(category1);
        assertEquals(category2, category1);
    }

    private Category generateCategory() {
        return new Category(new Identity("1"), "test");
    }
}