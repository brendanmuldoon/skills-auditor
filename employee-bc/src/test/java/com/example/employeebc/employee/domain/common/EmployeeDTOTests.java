package com.example.employeebc.employee.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeDTOTests {

    @Test
    @DisplayName("When two objects are created their state would be the same")
    void test01() {
        EmployeeDTO employeeDTO = generateEmployeeDTO();
        EmployeeDTO employeeDTO1 = new EmployeeDTO(employeeDTO);
        assertEquals(employeeDTO, employeeDTO1);
    }

    private EmployeeDTO generateEmployeeDTO() {
        return new EmployeeDTO(
                "1234",
                "test",
                "test",
                "test",
                "test",
                "test",
                Role.MANAGER,
                "test",
                "test"
        );
    }

}