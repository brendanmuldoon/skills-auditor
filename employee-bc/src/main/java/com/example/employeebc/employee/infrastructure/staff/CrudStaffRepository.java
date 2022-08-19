package com.example.employeebc.employee.infrastructure.staff;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudStaffRepository extends CrudRepository<StaffJpa, String> {

    @Query(
            value = "SELECT * FROM employee JOIN staff_skills ON staff_skills.staff_id = employee.id WHERE staff_skills.expiry < CURRENT_DATE",
            nativeQuery = true
    )
    Iterable<StaffJpa> findAllWithExpiredSkills();
}
