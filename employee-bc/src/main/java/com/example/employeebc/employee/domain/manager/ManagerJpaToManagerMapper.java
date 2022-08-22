package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.application.manager.interfaces.IManagerJpaToManagerMapper;
import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import com.example.employeebc.employee.infrastructure.manager.ManagerTeamJpaValueObject;
import org.springframework.stereotype.Component;

@Component
public class ManagerJpaToManagerMapper implements IManagerJpaToManagerMapper  {

    @Override
    public Manager map(ManagerJpa managerJpa) {

        Manager manager = Manager.managerOf(EmployeeJpaToEmployeeMapper.map(managerJpa.getId(),
                managerJpa.getFullname_firstname(),
                managerJpa.getFullname_surname(),
                managerJpa.getAddress_housenumber(),
                managerJpa.getAddress_streetname(),
                managerJpa.getAddress_postcode(),
                Role.valueOf(managerJpa.getRole().toUpperCase()),
                managerJpa.getSecuritycredentials_username(),
                managerJpa.getSecuritycedentials_password()));

        for (ManagerTeamJpaValueObject mt : managerJpa.getTeam()) {

            ManagerTeam managerTeam = ManagerTeam.managerTeamOf(mt.getStaff().getId(), mt.getManager(), new FullName(mt.getFullname_firstname(), mt.getFullname_surname()));
            manager.addTeamMember(managerTeam);

        }

        return manager;
    }
}
