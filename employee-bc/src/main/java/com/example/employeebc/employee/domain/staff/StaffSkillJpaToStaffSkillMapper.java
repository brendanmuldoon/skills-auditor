package com.example.employeebc.employee.domain.staff;

import com.example.employeebc.employee.application.staff.interfaces.IStaffSkillJpaToStaffSkillMapper;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;
import org.springframework.stereotype.Component;

@Component
public class StaffSkillJpaToStaffSkillMapper implements IStaffSkillJpaToStaffSkillMapper {
    @Override
    public StaffSkill map(StaffSkillJpaValueObject staffSkillJpaValueObject) {
        ExpirationDate expirationDate = new ExpirationDate(
                staffSkillJpaValueObject.getExpiry().getMonthValue(),
                staffSkillJpaValueObject.getExpiry().getYear()
        );

        StaffSkill staffSkill = StaffSkill.staffSkillOf(
                staffSkillJpaValueObject.getSkillId(),
                StrengthOfSkill.valueOf(staffSkillJpaValueObject.getStrengthOfSkill().toUpperCase()),
                expirationDate
        );

        staffSkill.setId(staffSkillJpaValueObject.getId());

        return staffSkill;
    }
}
