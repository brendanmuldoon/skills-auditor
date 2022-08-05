package com.example.employeebc.employee.application.manager.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCreateSkillEvent {

    private String id;
    private String description;
    private String category;
}
