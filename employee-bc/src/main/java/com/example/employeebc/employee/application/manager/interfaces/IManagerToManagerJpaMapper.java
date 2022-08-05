package com.example.employeebc.employee.application.manager.interfaces;

import com.example.employeebc.employee.domain.manager.Manager;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;

public interface IManagerToManagerJpaMapper {
    ManagerJpa map(Manager manager);
}
