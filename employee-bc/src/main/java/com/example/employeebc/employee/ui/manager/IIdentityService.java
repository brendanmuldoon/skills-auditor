package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.application.manager.commands.UserDetails;

public interface IIdentityService {

    String validateAndGetRole(UserDetails command);

    String getID(UserDetails command);

    boolean isSpecifiedUser(UserDetails command, String buyer_id);


    boolean isAdmin(UserDetails command);
}
