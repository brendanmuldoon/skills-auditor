package com.example.employeebc.employee.application.staff.mappers;

import com.example.employeebc.employee.domain.common.Role;
import com.example.employeebc.employee.domain.staff.DTO.StaffDTO;
import com.example.employeebc.employee.domain.staff.DTO.StaffSkillDTO;
import com.example.employeebc.employee.domain.staff.ExpirationDate;
import com.example.employeebc.employee.domain.staff.StrengthOfSkill;
import com.example.employeebc.employee.domain.staff.interfaces.IStaffJpa;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffJpaToDTOMapper {

    private StaffJpaToDTOMapper(){}

    public static Optional<StaffDTO> convertStaffDetailsToDTO(IStaffJpa staff) {

        StaffDTO staffDTO = mapToStaffDTO(staff);

        return Optional.of(staffDTO);
    }

    private static StaffDTO mapToStaffDTO(IStaffJpa staff) {

        List<StaffSkillDTO> skills = convertStaffSkillsToDTO(staff);

        return new StaffDTO(
                staff.getId(),
                staff.getFullname_firstname(),
                staff.getFullname_surname(),
                staff.getAddress_housenumber(),
                staff.getAddress_streetname(),
                staff.getAddress_postcode(),
                Role.valueOf(staff.getRole().toUpperCase()),
                staff.getSecuritycredentials_username(),
                staff.getSecuritycedentials_password(),
                skills
        );

    }


    public static List<StaffSkillDTO> convertStaffSkillsToDTO(IStaffJpa staff) {
        List<StaffSkillDTO> skills = new ArrayList<>();
        for (StaffSkillJpaValueObject s : staff.getSkills()) {
            ExpirationDate expiry = new ExpirationDate(
                    s.getExpiry().getMonthValue(),
                    s.getExpiry().getYear());
            StaffSkillDTO skill = new StaffSkillDTO(
                    s.getSkillId(),
                    StrengthOfSkill.valueOf(s.getStrengthOfSkill().toUpperCase()),
                    expiry);
            skills.add(skill);
        }
        return skills;
    }

}
