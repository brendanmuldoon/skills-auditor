package com.example.employeebc.employee.infrastructure.staff;

import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class StaffRepository implements IStaffRepository {

    private CrudStaffRepository repository;
    @Override
    public Iterable<StaffJpa> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<StaffJpa> findById(String staffId) {
        return repository.findById(staffId);
    }

    @Override
    public StaffJpa save(StaffJpa staffJpa) {
        return repository.save(staffJpa);
    }

    @Override
    public void delete(StaffJpa staffJpa) {
        repository.delete(staffJpa);
    }


}
