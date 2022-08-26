package com.example.employeebc.employee.application.staff.mappers;

import com.example.employeebc.employee.domain.common.Role;
import com.example.employeebc.employee.application.staff.dto.StaffDTO;
import com.example.employeebc.employee.application.staff.dto.StaffSkillDTO;
import com.example.employeebc.employee.domain.staff.ExpirationDate;
import com.example.employeebc.employee.domain.staff.StrengthOfSkill;
import com.example.employeebc.employee.domain.staff.interfaces.IStaffJpa;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffJpaToDTOMapper {

    private StaffJpaToDTOMapper(){}


    public static List<StaffDTO> convertStaffJpaListToDTO(Iterable<StaffJpa> response) {
        List<StaffDTO> staff = new ArrayList<>();
        for (StaffJpa s : response) {
            List<StaffSkillDTO> skills = getListOfExpiredSkills(s);

            StaffDTO staffDTO = new StaffDTO(
                    s.getId(),
                    s.getFullname_firstname(),
                    s.getFullname_surname(),
                    s.getAddress_housenumber(),
                    s.getAddress_streetname(),
                    s.getAddress_postcode(),
                    Role.valueOf(s.getRole().toUpperCase()),
                    s.getSecuritycredentials_username(),
                    s.getSecuritycedentials_password(),
                    skills
            );
            staff.add(staffDTO);
        }
        return staff;
    }

    private static List<StaffSkillDTO> getListOfExpiredSkills(IStaffJpa s) {
        List<StaffSkillDTO> skills = new ArrayList<>();

        for (StaffSkillJpaValueObject ss : s.getSkills()) {
            if(ss.getExpiry().isBefore(LocalDate.now())) {
                ExpirationDate expiry = new ExpirationDate();
                expiry.setExpiry(ss.getExpiry().getYear(), ss.getExpiry().getMonthValue());
                StaffSkillDTO skill = new StaffSkillDTO(
                        ss.getSkillId(),
                        StrengthOfSkill.valueOf(ss.getStrengthOfSkill().toUpperCase()),
                        expiry);
                skills.add(skill);
            }
        }
        return skills;

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
