package com.example.employeebc.employee.domain.manager.DTO;

import com.example.employeebc.employee.application.manager.dto.ManagerDTO;
import com.example.employeebc.employee.domain.common.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerDTOTests {

    @Test
    @DisplayName("When two objects are created their state would be the same")
    void test01() {
        ManagerDTO managerDTO1 = generateManagerDTO();
        ManagerDTO managerDTO2 = new ManagerDTO(managerDTO1);
        assertEquals(managerDTO2, managerDTO1);
    }

    private ManagerDTO generateManagerDTO() {
        return new ManagerDTO(
                "1234",
                "test",
                "test",
                "test",
                "test",
                "test",
                Role.MANAGER,
                "test",
                "test",
                new ArrayList<>()
        );
    }
}