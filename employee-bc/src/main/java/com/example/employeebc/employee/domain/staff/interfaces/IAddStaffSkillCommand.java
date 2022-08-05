package com.example.employeebc.employee.domain.staff.interfaces;

import com.example.employeebc.employee.domain.staff.ExpirationDate;

public interface IAddStaffSkillCommand {

    String getSkillId();
    String getStrengthOfSkill();
    ExpirationDate getExpirationDate();
    String getStaffId();
}
