package com.example.employeebc.employee.domain.staff;

import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffSkillCommand;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Staff extends Employee { // Aggregate
    private List<StaffSkill> skills;

    public Staff(Identity id, FullName fullName, Address address, Role role, SecurityCredentials securityCredentials) {
        super(id, fullName, address, role, securityCredentials);
        this.skills = new ArrayList<>();
    }

    public static Staff staffOf(Identity id, FullName fullName, Address address, Role role, SecurityCredentials securityCredentials) {
        return new Staff(id, fullName, address, role, securityCredentials);
    }

    public List<StaffSkill> retrieveAllSkills() {
        return skills;
    }

    public StaffSkill addSkill(StaffSkill newSkill) { // do nothing
        if(!skills.contains(newSkill)) {
            this.skills.add(newSkill);
        }
        return newSkill;
    }

    public void removeASkill(String skillId) { // do nothing
        for(StaffSkill skill : skills) {
            if (skill.skillId().equals(skillId)) {
                skills.remove(skill);
                break;
            }
        }
    }

    public void updateAStaffSkill(IUpdateStaffSkillCommand skill) {
        for(StaffSkill s : skills) {
            if(s.skillId().equals(skill.getSkillId())) {
                s.setSkillId(skill.getSkillId());
                s.setStrengthOfSkill(StrengthOfSkill.valueOf(skill.getStrengthOfSkill()));
                s.setExpiry(skill.getExpirationDate());
                break;
            }
        }
    }


    public boolean retrieveSkillById(String id) {
        for(StaffSkill s : skills) {
            if(s.skillId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
