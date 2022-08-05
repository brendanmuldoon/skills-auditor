package com.example.employeebc.employee.domain.staff.DTO;

import com.example.employeebc.employee.domain.common.EmployeeDTO;
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

}
