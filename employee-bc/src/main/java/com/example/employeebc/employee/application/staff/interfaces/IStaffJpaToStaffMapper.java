package com.example.employeebc.employee.application.staff.interfaces;

import com.example.employeebc.employee.domain.staff.Staff;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;

public interface IStaffJpaToStaffMapper {

    Staff map(StaffJpa staffJpa);
}
