package com.example.employeebc.employee.ui.staff;

import com.example.employeebc.employee.application.staff.dto.StaffDTO;

import java.util.List;

public interface IStaffQueryHandler {

    List<StaffDTO> findAllWithExpiredSkills();
}
