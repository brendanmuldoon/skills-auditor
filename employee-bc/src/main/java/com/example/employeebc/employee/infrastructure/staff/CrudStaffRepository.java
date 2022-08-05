package com.example.employeebc.employee.infrastructure.staff;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudStaffRepository extends CrudRepository<StaffJpa, String> {

}
