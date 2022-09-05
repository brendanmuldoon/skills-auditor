package com.example.employeebc.employee.application.manager.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeUpdateUsernameAndPasswordEvent {

    private String id;
    private String username;
    private String password;

}
