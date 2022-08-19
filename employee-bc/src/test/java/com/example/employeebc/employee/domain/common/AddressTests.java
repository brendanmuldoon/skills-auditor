package com.example.employeebc.employee.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressTests {

    private Address generateAddress(){
        return new Address("1","Street1","BT123BG");
    }

    @Test
    @DisplayName("When two value objects are created their state would be the same")
    void test01() {
        Address address1 = generateAddress();
        Address address2 = new Address(address1);
        assertEquals(address1, address2);
    }

    @Test
    @DisplayName("When an empty value is passed for the house number then IllegalArgumentException is thrown")
    void test02() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Address("", "test", "test");
        });
    }

    @Test
    @DisplayName("When an empty value is passed for the street name then IllegalArgumentException is thrown")
    void test03() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Address("1", "", "test");
        });
    }

    @Test
    @DisplayName("When an empty value is passed for the post code then IllegalArgumentException is thrown")
    void test04() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Address("1", "test", "");
        });
    }

}