package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.domain.common.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Manager extends Employee { // Aggregate

    private List<ManagerTeam> team;

    public Manager(Identity id, FullName fullName, Address address, Role role, SecurityCredentials securityCredentials) {
        super(id, fullName, address, role, securityCredentials);
        this.team = new ArrayList<>();
    }

    public static Manager managerOf(Identity identity, FullName fullName, Address address, Role role, SecurityCredentials securityCredentials) {
        return new Manager(identity, fullName, address, role, securityCredentials);

    }

    public List<ManagerTeam> retrieveTeam() {
        return team;
    }

    public void addTeamMember(ManagerTeam teamMember) {
        if(!team.contains(teamMember)) {
            this.team.add(teamMember);
        } else {
            throw new IllegalArgumentException("Team member already exists");

        }
    }

    // remove team member
    public void removeTeamMember(String staffId) {
        for(ManagerTeam t : team) {
            if(t.staffId().equals(staffId)) {
                team.remove(t);
                break;
            }
        }
    }

    // add to team

    // remove team member

    // get single team member
}
