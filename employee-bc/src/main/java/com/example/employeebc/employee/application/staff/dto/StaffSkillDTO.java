package com.example.employeebc.employee.application.staff.dto;

import com.example.employeebc.employee.domain.staff.ExpirationDate;
import com.example.employeebc.employee.domain.staff.StrengthOfSkill;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class StaffSkillDTO {
    private String skillId;
    private StrengthOfSkill strengthOfSkill;
    private ExpirationDate expiry;

    public StaffSkillDTO(StaffSkillDTO staffSkillDTO) {
        this(staffSkillDTO.getSkillId(), staffSkillDTO.getStrengthOfSkill(), staffSkillDTO.getExpiry());
    }
}
