package com.example.employeebc.employee.application.manager.interfaces;

import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;

import java.util.Optional;

public interface IManagerRepository {


    Iterable<ManagerJpa> findAll();

    Optional<ManagerJpa> findById(String managerId);

    void save(ManagerJpa managerJpa);
}
