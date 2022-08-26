package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.application.manager.dto.EmployeeSkillDTOList;
import com.example.employeebc.employee.application.manager.dto.ManagerTeamDTO;
import com.example.employeebc.employee.domain.manager.ManagerTeam;

import java.util.List;

public interface IManagerQueryHandler {

    List<ManagerTeam> findTeamByManagerId(String managerId);

    List<ManagerTeamDTO> findTeamBySkillId(String managerId, String skillId);

    EmployeeSkillDTOList findSkillsByCategory(String categoryId);

    List<?> findAllWithExpiredSkills();
}
