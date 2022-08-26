package com.example.employeebc.employee.application.manager.dto;

import com.example.employeebc.employee.application.staff.dto.StaffSkillDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ManagerTeamDTO {
    private String staffId;
    private String staffFirstName;
    private String staffSurname;
    private List<StaffSkillDTO> skills;

    public ManagerTeamDTO(ManagerTeamDTO managerTeamDTO) {
        this(managerTeamDTO.getStaffId(), managerTeamDTO.getStaffFirstName(),
                managerTeamDTO.getStaffSurname(), managerTeamDTO.getSkills());
    }
}
