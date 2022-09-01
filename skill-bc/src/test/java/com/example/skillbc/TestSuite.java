package com.example.skillbc;

import com.example.skillbc.skill.application.category.CategoryApplicationServiceTests;
import com.example.skillbc.skill.application.skill.SkillApplicationServiceTests;
import com.example.skillbc.skill.application.skill.SkillQueryHandlerTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
        {
                CategoryApplicationServiceTests.class,
                SkillApplicationServiceTests.class,
                SkillQueryHandlerTests.class
        }
)
public class TestSuite {
}
