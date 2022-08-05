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
        Identity identity = new Identity(managerJpa.getId());
        FullName fullName = new FullName(managerJpa.getFullname_firstname(), managerJpa.getFullname_surname());
        Address address = new Address(managerJpa.getAddress_housenumber(), managerJpa.getAddress_streetname(), managerJpa.getAddress_postcode());
        Role role = Role.valueOf(managerJpa.getRole().toUpperCase());
        SecurityCredentials securityCredentials = new SecurityCredentials(managerJpa.getSecuritycredentials_username(), managerJpa.getSecuritycedentials_password());

        Manager manager = Manager.managerOf(identity, fullName, address, role, securityCredentials);

        for (ManagerTeamJpaValueObject mt : managerJpa.getTeam()) {

            ManagerTeam managerTeam = ManagerTeam.managerTeamOf(mt.getStaff().getId(), mt.getManager());
            manager.addTeamMember(managerTeam);

        }

        return manager;
    }
}
