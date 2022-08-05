package com.example.employeebc.employee.application.manager.commands;

import com.example.employeebc.employee.domain.manager.interfaces.IUpdateManagerTeamCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateManagerTeamCommand implements IUpdateManagerTeamCommand {

    private String managerId;
    private String staffId;
}
