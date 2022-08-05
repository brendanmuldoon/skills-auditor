package com.example.employeebc.employee.application.staff.commands;

import com.example.employeebc.employee.domain.staff.ExpirationDate;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffSkillCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateStaffSkillCommand implements IUpdateStaffSkillCommand {

    private String skillId;
    private String strengthOfSkill;
    private ExpirationDate expirationDate;
    private String staffId;


}
