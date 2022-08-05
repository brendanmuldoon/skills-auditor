package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.application.manager.interfaces.IManagerToManagerJpaMapper;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import org.springframework.stereotype.Component;

@Component
public class ManagerToManagerJpaMapper implements IManagerToManagerJpaMapper {
    @Override
    public ManagerJpa map(Manager manager) {
        ManagerJpa managerJpa = ManagerJpa.managerJpaOf(
                manager.id().id(),
                manager.fullName().firstName(),
                manager.fullName().surname(),
                manager.address().houseNumber(),
                manager.address().streetName(),
                manager.address().postcode(),
                manager.role().getEmployeeRole(),
                manager.securityCredentials().username(),
                manager.securityCredentials().password()
        );


        return managerJpa;
    }
}
