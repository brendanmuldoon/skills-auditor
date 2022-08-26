package com.example.employeebc.employee.application.staff;

import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
import com.example.employeebc.employee.application.staff.mappers.StaffJpaToDTOMapper;
import com.example.employeebc.employee.application.staff.dto.StaffDTO;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.example.employeebc.employee.ui.staff.IStaffQueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class StaffQueryHandler implements IStaffQueryHandler {

    private IStaffRepository staffRepository;

    @Override
    public List<StaffDTO> findAllWithExpiredSkills() {
        Iterable<StaffJpa> response = staffRepository.findAllWithExpiredSkills();
        if(response != null) {
            return StaffJpaToDTOMapper.convertStaffJpaListToDTO(response);
        }
        return new ArrayList<>();
    }
}
