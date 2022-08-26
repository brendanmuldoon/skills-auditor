package com.example.employeebc.employee.ui.staff;

import com.example.employeebc.employee.domain.staff.interfaces.IAddStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IRemoveStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffDetailsCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffSkillCommand;

public interface IStaffApplicationService {

    void removeStaffSkill(IRemoveStaffSkillCommand removeSkillCommand);

    void addStaffSkill(IAddStaffSkillCommand addStaffSkillCommand);

    void updateStaffDetails(IUpdateStaffDetailsCommand updateStaffDetailsCommand);

    void updateStaffSkill(IUpdateStaffSkillCommand updateStaffSkillCommand);
}
