package com.example.employeebc.employee.domain.staff;

import com.example.employeebc.employee.domain.common.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ExpirationDateTests {

    @Test
    @DisplayName("When two value objects are created their state would be the same")
    void test01() {
        ExpirationDate expirationDate = generateExpirationDate();
        ExpirationDate expirationDate1 = new ExpirationDate(expirationDate);
        assertEquals(expirationDate, expirationDate1);
    }

    @DisplayName("When supplied with with out of bounds values then IllegalArgumentException is thrown")
    @ValueSource(ints = {0, 13})
    @ParameterizedTest
    void test02(int month) {
        assertThrows(IllegalArgumentException.class, () -> {
            new ExpirationDate(month, 2023);
        });
    }

    @Test
    @DisplayName("When supplied with a year less than the current year then an IllegalArgumentException is thrown")
    void test03() {
        assertThrows(IllegalArgumentException.class , () -> {
           new ExpirationDate(1, 2021);
        });
    }

    private ExpirationDate generateExpirationDate() {
        return new ExpirationDate(1, 2023);
    }

}