package com.example.employeebc.employee.domain.staff.interfaces;

import com.example.employeebc.employee.domain.common.Address;
import com.example.employeebc.employee.domain.common.FullName;
import com.example.employeebc.employee.domain.common.SecurityCredentials;

public interface IUpdateStaffDetailsCommand {
    String getStaffId();

    FullName getFullName();

    Address getAddress();

    String getRole();

    SecurityCredentials getSecurityCredentials();
}
