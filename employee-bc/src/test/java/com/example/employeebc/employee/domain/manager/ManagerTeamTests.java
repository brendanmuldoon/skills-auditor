package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.domain.common.FullName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTeamTests {

    private ManagerTeam generateManagerTeam() {
        return new ManagerTeam("0000", "0000", new FullName("test", "test"));
    }

    @Test
    @DisplayName("When two value objects are created their state would be the same")
    void test01() {
        ManagerTeam managerTeam1 = generateManagerTeam();
        ManagerTeam managerTeam2 = new ManagerTeam(managerTeam1);
        assertEquals(managerTeam1, managerTeam2);
    }

    @Test
    @DisplayName("When empty staffId is supplied then an IllegalArgumentException is thrown")
    void test02() {
        assertThrows(IllegalArgumentException.class, () -> {
           new ManagerTeam("", "0000", new FullName("test", "test"));
        });
    }

    @Test
    @DisplayName("When empty managerId is supplied then an IllegalArgumentException is thrown")
    void test03() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ManagerTeam("0000", "", new FullName("test", "test"));
        });
    }


}