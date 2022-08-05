package com.example.employeebc.employee.application.staff.interfaces;

import com.example.employeebc.employee.domain.staff.StaffSkill;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;
import org.springframework.stereotype.Component;

@Component
public interface IStaffSkillJpaToStaffSkillMapper {
    StaffSkill map(StaffSkillJpaValueObject staffSkillJpaValueObject);

}
