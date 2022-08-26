package com.example.employeebc.employee.application.manager.dto;

import com.example.employeebc.employee.domain.common.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class EmployeeDTO { // base DTO for staff and manager

    private String id;
    private String fullname_firstname;
    private String fullname_surname;
    private String houseNumber;
    private String streetName;
    private String postcode;
    private Role role;
    private String username;
    private String password;

    public EmployeeDTO(String id,
                      String fullname_firstname,
                      String fullname_surname,
                      String houseNumber,
                      String streetName,
                      String postcode,
                      Role role,
                      String username,
                      String password) {
        this.id = id;
        this.fullname_firstname = fullname_firstname;
        this.fullname_surname = fullname_surname;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.postcode = postcode;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public EmployeeDTO(EmployeeDTO employeeDTO) {
        this(employeeDTO.getId(), employeeDTO.getFullname_firstname(), employeeDTO.getFullname_surname(),
                employeeDTO.getHouseNumber(), employeeDTO.getStreetName(), employeeDTO.getPostcode(),
                employeeDTO.getRole(), employeeDTO.getPassword(), employeeDTO.getPassword());
    }
}
