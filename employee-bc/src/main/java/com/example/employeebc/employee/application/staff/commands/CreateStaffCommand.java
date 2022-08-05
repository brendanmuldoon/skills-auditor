package com.example.employeebc.employee.application.staff.commands;

import com.example.employeebc.employee.domain.staff.interfaces.ICreateStaffCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStaffCommand implements ICreateStaffCommand {

    private String firstName;
    private String surname;
    private String houseNumber;
    private String streetName;
    private String postcode;
    private String role;
    private String username;
    private String password;

}
