package com.example.employeebc.employee.domain.staff.DTO;

import com.example.employeebc.employee.application.staff.dto.StaffDTO;
import com.example.employeebc.employee.domain.common.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffDTOTests {

    @Test
    @DisplayName("When two objects are created their state would be the same")
    void test01() {
        StaffDTO staffDTO = generateStaffDTO();
        StaffDTO staffDTO1 = new StaffDTO(staffDTO);
        assertEquals(staffDTO, staffDTO1);
    }

    private StaffDTO generateStaffDTO() {
        return new StaffDTO("1234",
                "test",
                "test",
                "test",
                "test",
                "test",
                Role.MANAGER,
                "test",
                "test",
                new ArrayList<>());
    }
}