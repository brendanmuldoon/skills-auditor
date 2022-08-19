package com.example.employeebc.employee.ui.staff;

import com.example.employeebc.employee.domain.staff.DTO.StaffDTO;
import com.example.employeebc.employee.domain.staff.DTO.StaffSkillDTO;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;

import java.util.List;
import java.util.Optional;

public interface IStaffQueryHandler {

    List<StaffDTO> findAllWithExpiredSkills();
}
