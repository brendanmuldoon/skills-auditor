package com.example.employeebc.employee.application.manager.queries;

import com.example.employeebc.employee.domain.manager.interfaces.IGetTeamBySkillIdQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetTeamBySkillIdQuery implements IGetTeamBySkillIdQuery {

    private String managerId;
    private String skillId;

}
