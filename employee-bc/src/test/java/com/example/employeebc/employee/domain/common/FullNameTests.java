package com.example.employeebc.employee.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FullNameTests {

    private FullName generateFullName() {
        return new FullName("Test", "Test");
    }

    @Test
    @DisplayName("When two value objects are created their state would be the same")
    void test01() {
        FullName fullName1 = generateFullName();
        FullName fullName2 = new FullName(fullName1);
        assertEquals(fullName1, fullName2);
    }

    @Test
    @DisplayName("When an empty value is passed for the first name then IllegalArgumentException is thrown")
    void test02() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FullName("", "test");
        });
    }

    @Test
    @DisplayName("When an empty value is passed for the surname then IllegalArgumentException is thrown")
    void test03() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FullName("TEST", "");
        });
    }

}