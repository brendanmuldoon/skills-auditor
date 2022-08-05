package com.example.employeebc.employee.domain.staff;

import com.example.employeebc.employee.application.staff.interfaces.IStaffSkillToStaffSkillJpaMapper;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Component
public class StaffSkillToStaffSkillJpaMapper implements IStaffSkillToStaffSkillJpaMapper {
    @Override
    public StaffSkillJpaValueObject map(StaffSkill skill, String staffId) {
        LocalDate expirationDate = convertExpirationToLocaleDate(skill);

        StaffSkillJpaValueObject staffSkillJpaValueObject = StaffSkillJpaValueObject.staffSkillJpaOf(
                skill.getId(),
                skill.skillId(),
                skill.strengthOfSkill().getStrength(),
                expirationDate
        );

        staffSkillJpaValueObject.setId(skill.getId());
        staffSkillJpaValueObject.setStaff(staffId);




        return staffSkillJpaValueObject;
    }

    private LocalDate convertExpirationToLocaleDate(StaffSkill skill) {
        LocalDate expirationDate =LocalDate.of(skill.expiry().getYear(),
                skill.expiry().getMonth(),
                1);
        expirationDate.with(lastDayOfMonth());//set to last day of month
        return expirationDate;
    }
}

