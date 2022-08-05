package com.example.employeebc.employee.infrastructure.manager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudManagerRepository extends CrudRepository<ManagerJpa, String> {
}
