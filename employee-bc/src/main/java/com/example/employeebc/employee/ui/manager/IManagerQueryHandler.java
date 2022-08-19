package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.application.manager.queries.EmployeeSkillDTOList;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTO;

import java.util.List;

public interface IManagerQueryHandler {

    List<ManagerTeamDTO> findTeamByManagerId(String managerId);

    List<ManagerTeamDTO> findTeamBySkillId(String managerId, String skillId);

    EmployeeSkillDTOList findSkillsByCategory(String categoryId);

    List<?> findAllWithExpiredSkills();
}
