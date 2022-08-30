package com.example.skillbc;

import com.example.skillbc.skill.application.category.CategoryApplicationServiceTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CategoryApplicationServiceTests.class
})
public class TestSuit {
}
