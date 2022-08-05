package com.example.employeebc.employee.domain.staff;

import com.example.employeebc.employee.domain.common.IdentifiedValueObject;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffSkillCommand;
import lombok.Setter;
import lombok.ToString;

import static com.example.employeebc.ApplicationConstants.SKILL_ID_ERROR_MSG_EMPTY;

@Setter
@ToString
public class StaffSkill extends IdentifiedValueObject {

    private String skillId;
    private StrengthOfSkill strengthOfSkill;
    private ExpirationDate expiry;

    public StaffSkill(String skillId, StrengthOfSkill strengthOfSkill, ExpirationDate expiry) {
        super();
        setSkillId(skillId);
        setStrengthOfSkill(strengthOfSkill);
        setExpiry(expiry);
    }

    public static StaffSkill staffSkillOf(String skillId, StrengthOfSkill strengthOfSkill, ExpirationDate expiry) {
        return new StaffSkill(skillId, strengthOfSkill, expiry);
    }

    public void setExpiry(ExpirationDate expiry) {
        this.expiry = expiry;
    }

    public void setStrengthOfSkill(StrengthOfSkill strengthOfSkill) {
        this.strengthOfSkill = strengthOfSkill;
    }

    public void setSkillId(String skillId) {
        assertArgumentNotEmpty(skillId, SKILL_ID_ERROR_MSG_EMPTY);
        this.skillId = skillId;
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        StaffSkill staffSkill = (StaffSkill) o;

        return staffSkill.skillId.equals(this.skillId);

    }

    public String skillId() {
        return skillId;
    }

    public StrengthOfSkill strengthOfSkill() {
        return strengthOfSkill;
    }

    public ExpirationDate expiry() {
        return expiry;
    }

    public void updateStaffSkill(IUpdateStaffSkillCommand updateStaffSkillCommand) {
        this.strengthOfSkill = StrengthOfSkill.valueOf(updateStaffSkillCommand.getStrengthOfSkill());
        this.skillId = updateStaffSkillCommand.getSkillId();
        this.expiry = updateStaffSkillCommand.getExpirationDate();
    }
}
