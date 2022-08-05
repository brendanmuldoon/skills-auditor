package com.example.employeebc.employee.application.manager.queries;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class EmployeeSkillDTOList {

    private List<EmployeeSkillDTO> skills;

    public EmployeeSkillDTOList() {
        this.skills = new ArrayList<>();
    }
}
