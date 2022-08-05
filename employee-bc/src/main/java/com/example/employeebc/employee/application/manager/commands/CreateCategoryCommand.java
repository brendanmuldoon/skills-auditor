package com.example.employeebc.employee.application.manager.commands;

import com.example.employeebc.employee.domain.manager.interfaces.ICreateCategoryCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCategoryCommand implements ICreateCategoryCommand {

    private String description;
}
