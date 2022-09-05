package com.example.employeebc.employee.ui.staff;

import com.example.employeebc.employee.domain.staff.interfaces.IAddStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IRemoveStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffDetailsCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffSkillCommand;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IStaffApplicationService {

    void removeStaffSkill(IRemoveStaffSkillCommand removeSkillCommand);

    void addStaffSkill(IAddStaffSkillCommand addStaffSkillCommand);

    void updateStaffDetails(IUpdateStaffDetailsCommand updateStaffDetailsCommand) throws JsonProcessingException;

    void updateStaffSkill(IUpdateStaffSkillCommand updateStaffSkillCommand);
}
