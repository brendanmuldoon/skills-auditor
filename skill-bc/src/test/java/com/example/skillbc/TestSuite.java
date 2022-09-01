package com.example.skillbc;

import com.example.skillbc.skill.application.category.CategoryApplicationServiceTests;
import com.example.skillbc.skill.application.skill.SkillApplicationServiceTests;
import com.example.skillbc.skill.application.skill.SkillQueryHandlerTests;
import com.example.skillbc.skill.domain.common.IdentityTests;
import com.example.skillbc.skill.domain.skill.CategoryTests;
import com.example.skillbc.skill.domain.skill.CategoryToCategoryJpaMapperTests;
import com.example.skillbc.skill.domain.skill.DTO.CategoryDTOTests;
import com.example.skillbc.skill.domain.skill.DTO.SkillDTOTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
        {
                CategoryApplicationServiceTests.class,
                SkillApplicationServiceTests.class,
                SkillQueryHandlerTests.class,
                IdentityTests.class,
                CategoryDTOTests.class,
                SkillDTOTests.class,
                CategoryTests.class,
                CategoryToCategoryJpaMapperTests.class
        }
)
public class TestSuite {
}
