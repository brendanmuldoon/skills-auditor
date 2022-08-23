package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.domain.common.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerAggregateTests {

    private final Identity VALID_ID = UniqueIDFactory.createID();
    private final FullName VALID_FULLNAME = new FullName("first1","sur1");
    private final Address VALID_ADDRESS = new Address("1", "test", "test");
    private final Role VALID_ROLE = Role.MANAGER;
    private final SecurityCredentials VALID_SECURITY_CREDENTIALS = new SecurityCredentials("user", "pass");

    @Test
    @DisplayName("When valid details are assigned to a Manager then a Manager object is created")
    void test01() {
        assertDoesNotThrow(() -> {
            assertNotNull(Manager.managerOf(VALID_ID, VALID_FULLNAME, VALID_ADDRESS, VALID_ROLE, VALID_SECURITY_CREDENTIALS));
        });
    }

    @Test
    @DisplayName("When retrieving a valid team then the team will contain one object")
    void test02() {
        Manager manager = Manager.managerOf(VALID_ID, VALID_FULLNAME, VALID_ADDRESS, VALID_ROLE, VALID_SECURITY_CREDENTIALS);
        ManagerTeam mt = new ManagerTeam("1234", "1234", VALID_FULLNAME);
        manager.addTeamMember(mt);
        assertEquals(1, manager.retrieveTeam().size());
    }




}