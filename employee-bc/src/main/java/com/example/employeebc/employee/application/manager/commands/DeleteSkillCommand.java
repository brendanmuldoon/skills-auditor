package com.example.employeebc.employee.application.manager.commands;

import com.example.employeebc.employee.domain.manager.interfaces.IDeleteSkillCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteSkillCommand implements IDeleteSkillCommand {

    private String skillId;
}
