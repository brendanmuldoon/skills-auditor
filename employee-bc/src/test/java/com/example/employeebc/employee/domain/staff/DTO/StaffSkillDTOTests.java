package com.example.employeebc.employee.domain.staff.DTO;

import com.example.employeebc.employee.application.staff.dto.StaffSkillDTO;
import com.example.employeebc.employee.domain.staff.ExpirationDate;
import com.example.employeebc.employee.domain.staff.StrengthOfSkill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StaffSkillDTOTests {

    @Test
    @DisplayName("When two objects are created their state would be the same")
    void test01() {
        StaffSkillDTO staffSkillDTO = generateStaffSkillDTO();
        StaffSkillDTO staffSkillDTO1 = new StaffSkillDTO(staffSkillDTO);
        assertEquals(staffSkillDTO, staffSkillDTO1);
    }

    private StaffSkillDTO generateStaffSkillDTO() {
        return new StaffSkillDTO("0000", StrengthOfSkill.ADVANCED, new ExpirationDate(1, 2023));
    }

}