package com.example.employeebc.employee.application.manager.commands;

import com.example.employeebc.employee.domain.manager.interfaces.IEditCategoryCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditCategoryCommand implements IEditCategoryCommand {

    private String id;
    private String description;
}
