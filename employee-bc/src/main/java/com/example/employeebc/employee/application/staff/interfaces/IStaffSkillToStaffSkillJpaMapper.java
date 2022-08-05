package com.example.employeebc.employee.application.staff.interfaces;

import com.example.employeebc.employee.domain.staff.StaffSkill;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;

public interface IStaffSkillToStaffSkillJpaMapper {
    StaffSkillJpaValueObject map(StaffSkill skill, String staffId);
}
