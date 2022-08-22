package com.example.employeebc.employee.domain.staff;

import com.example.employeebc.employee.application.staff.interfaces.IStaffJpaToStaffMapper;
import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;
import org.springframework.stereotype.Component;

@Component
public class StaffJpaToStaffMapper implements IStaffJpaToStaffMapper {

    @Override
    public Staff map(StaffJpa staffJpa) {

        Staff staff = Staff.staffOf(EmployeeJpaToEmployeeMapper.map(
                staffJpa.getId(),
                staffJpa.getFullname_firstname(),
                staffJpa.getFullname_surname(),
                staffJpa.getAddress_housenumber(),
                staffJpa.getAddress_streetname(),
                staffJpa.getAddress_postcode(),
                Role.valueOf(staffJpa.getRole().toUpperCase()),
                staffJpa.getSecuritycredentials_username(),
                staffJpa.getSecuritycedentials_password()
        ));

        for (StaffSkillJpaValueObject staffSkillJpa : staffJpa.getSkills()) {

            ExpirationDate expirationDate = new ExpirationDate(staffSkillJpa.getExpiry().getMonthValue(),
                    staffSkillJpa.getExpiry().getYear());

            StaffSkill skill = new StaffSkill(
                    staffSkillJpa.getSkillId(),
                    StrengthOfSkill.valueOf(staffSkillJpa.getStrengthOfSkill().toUpperCase()),
                    expirationDate
            );
            skill.setId(staffSkillJpa.getId());
            staff.addSkill(skill);
        }
        return staff;
    }
}
