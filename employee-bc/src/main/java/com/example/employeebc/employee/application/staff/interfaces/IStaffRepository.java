package com.example.employeebc.employee.application.staff.interfaces;

import com.example.employeebc.employee.infrastructure.staff.StaffJpa;

import java.util.Optional;

public interface IStaffRepository {
    Iterable<StaffJpa> findAll();

    Optional<StaffJpa> findById(String staffId);

    StaffJpa save(StaffJpa staffJpa);

    void delete(StaffJpa staffJpa);
}
