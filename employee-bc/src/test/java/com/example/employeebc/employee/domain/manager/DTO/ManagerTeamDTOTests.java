package com.example.employeebc.employee.domain.manager.DTO;

import com.example.employeebc.employee.application.manager.dto.ManagerTeamDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTeamDTOTests {

    @Test
    @DisplayName("When two objects are created their state would be the same")
    void test01() {
        ManagerTeamDTO managerTeamDTO = generateManagerTeamDTO();
        ManagerTeamDTO managerTeamDTO1 = new ManagerTeamDTO(managerTeamDTO);
        assertEquals(managerTeamDTO, managerTeamDTO1);
    }

    private ManagerTeamDTO generateManagerTeamDTO() {
        return new ManagerTeamDTO(
                "0000",
                "test",
                "test",
                new ArrayList<>()
        );
    }

}