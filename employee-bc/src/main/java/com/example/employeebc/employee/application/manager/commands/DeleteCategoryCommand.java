package com.example.employeebc.employee.application.manager.commands;

import com.example.employeebc.employee.domain.manager.interfaces.IDeleteCategoryCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCategoryCommand  implements IDeleteCategoryCommand {

    private String categoryId;

    private String id;
    private String token;
    private String username;
}
