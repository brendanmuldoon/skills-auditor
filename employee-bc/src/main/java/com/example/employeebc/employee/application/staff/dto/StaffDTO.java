package com.example.employeebc.employee.application.staff.dto;

import com.example.employeebc.employee.application.manager.dto.EmployeeDTO;
import com.example.employeebc.employee.domain.common.Role;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class StaffDTO extends EmployeeDTO {
    private List<StaffSkillDTO> skills;

    public StaffDTO(String id,
                    String fullname_firstname,
                    String fullname_surname,
                    String houseNumber,
                    String streetName,
                    String postcode,
                    Role role,
                    String username,
                    String password,
                    List<StaffSkillDTO> skills) {
        super(id, fullname_firstname, fullname_surname, houseNumber, streetName, postcode, role, username, password);
        this.skills = skills;
    }

    public StaffDTO(StaffDTO staffDTO) {
        this(staffDTO.getId(), staffDTO.getFullname_firstname(),
                staffDTO.getFullname_surname(), staffDTO.getHouseNumber(),
                staffDTO.getStreetName(), staffDTO.getPostcode(),
                staffDTO.getRole(), staffDTO.getUsername(), staffDTO.getPassword(), staffDTO.getSkills());
    }
}
