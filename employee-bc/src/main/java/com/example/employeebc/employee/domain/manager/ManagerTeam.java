package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.domain.common.IdentifiedValueObject;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class ManagerTeam extends IdentifiedValueObject {

    private String staffId;
    private String managerId;

    public ManagerTeam(String staffId, String managerId) {
        super();
        setStaffId(staffId);
        setManagerId(managerId);
    }

    public static ManagerTeam managerTeamOf(String staffId, String managerId) {
        return new ManagerTeam(staffId, managerId);
    }

    public void setStaffId(String staffId) {
        assertArgumentNotEmpty(staffId, "Staff ID cannot be empty");
        this.staffId=staffId;
    }

    public void setManagerId(String managerId) {
        assertArgumentNotEmpty(managerId, "Manager ID cannot be empty");
        this.managerId = managerId;
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        ManagerTeam managerTeam = (ManagerTeam) o;

        return managerTeam.staffId.equals(this.staffId);

    }


    public String staffId() {
        return staffId;
    }

    public String managerId() {
        return managerId;
    }
}
