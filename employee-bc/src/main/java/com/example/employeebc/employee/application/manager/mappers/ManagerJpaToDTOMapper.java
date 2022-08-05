package com.example.employeebc.employee.application.manager.mappers;

import com.example.employeebc.employee.application.staff.mappers.StaffJpaToDTOMapper;
import com.example.employeebc.employee.domain.common.Role;
import com.example.employeebc.employee.domain.manager.DTO.ManagerDTO;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTO;
import com.example.employeebc.employee.domain.manager.interfaces.IManagerJpa;
import com.example.employeebc.employee.domain.staff.DTO.StaffSkillDTO;
import com.example.employeebc.employee.infrastructure.manager.ManagerTeamJpaValueObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerJpaToDTOMapper {

    private ManagerJpaToDTOMapper(){}

    public static Optional<ManagerDTO> convertManagerDetailsToDTO(IManagerJpa managerJpa) {

        ManagerDTO managerDTO = mapToManagerDTO(managerJpa);

        return Optional.of(managerDTO);

    }

    private static ManagerDTO mapToManagerDTO(IManagerJpa managerJpa) {

        List<ManagerTeamDTO> team = convertManagerTeamToDTO(managerJpa);

        return new ManagerDTO(
                managerJpa.getId(),
                managerJpa.getFullname_firstname(),
                managerJpa.getFullname_surname(),
                managerJpa.getAddress_housenumber(),
                managerJpa.getAddress_streetname(),
                managerJpa.getAddress_postcode(),
                Role.valueOf(managerJpa.getRole().toUpperCase()),
                managerJpa.getSecuritycredentials_username(),
                managerJpa.getSecuritycedentials_password(),
                team
        );
    }

    public static List<ManagerTeamDTO> convertManagerTeamToDTO(IManagerJpa managerJpa) {
        List<ManagerTeamDTO> team = new ArrayList<>();
        for (ManagerTeamJpaValueObject t : managerJpa.getTeam()) {

            List<StaffSkillDTO> skill = StaffJpaToDTOMapper.convertStaffSkillsToDTO(t.getStaff());
            ManagerTeamDTO managerTeamDTO = new ManagerTeamDTO(
                    t.getStaff().getId(),
                    t.getStaff().getFullname_firstname(),
                    t.getStaff().getFullname_surname(),
                    skill);
            team.add(managerTeamDTO);
        }
        return team;
    }
}
