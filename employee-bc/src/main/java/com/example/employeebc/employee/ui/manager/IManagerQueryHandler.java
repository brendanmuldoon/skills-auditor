package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.application.manager.queries.EmployeeSkillDTOList;
import com.example.employeebc.employee.domain.manager.DTO.ManagerDTO;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTO;
import com.example.employeebc.employee.domain.manager.interfaces.IGetTeamBySkillIdQuery;

import java.util.List;
import java.util.Optional;

public interface IManagerQueryHandler {
    Iterable<?> findAll();

    Optional<ManagerDTO> findByManagerId(String managerId);

    List<ManagerTeamDTO> findTeamByManagerId(String managerId);

    List<ManagerTeamDTO> findTeamBySkillId(IGetTeamBySkillIdQuery getTeamBySkillIdQuery);

    EmployeeSkillDTOList findSkillsByCategory(String categoryId);
}
