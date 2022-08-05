package com.example.employeebc.employee.application.staff.commands;

import com.example.employeebc.employee.domain.staff.interfaces.IDeleteStaffCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DeleteStaffCommand implements IDeleteStaffCommand {

    private String staffId;

}
