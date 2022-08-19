package com.example.employeebc.employee.domain.common;

import com.example.employeebc.employee.application.staff.commands.UpdateStaffDetailsCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EmployeeTests {

    private final Identity VALID_ID = UniqueIDFactory.createID();
    private final FullName VALID_FULL_NAME = new FullName("TEST", "TEST");
    private final SecurityCredentials VALID_SECURITY_CREDENTIALS = new SecurityCredentials("user", "pass");
    private final Address VALID_ADDRESS = new Address("1", "test", "test");

    @Test
    @DisplayName("When valid details are assigned to an Employee then an Employee is created")
    void test01() {
        assertDoesNotThrow(() -> {
            generateEmployee();
        });
    }

    @Test
    @DisplayName("When valid details are assigned to update an Employee then an Employee is updated")
    void test02() {
        Employee employee = generateEmployee();
        assertDoesNotThrow(() -> {
            employee.updateStaffDetails(generateUpdateEmployee());
        });
    }

    private UpdateStaffDetailsCommand generateUpdateEmployee() {
        UpdateStaffDetailsCommand updateStaffDetailsCommand = new UpdateStaffDetailsCommand();
        updateStaffDetailsCommand.setFullName(VALID_FULL_NAME);
        updateStaffDetailsCommand.setAddress(VALID_ADDRESS);
        updateStaffDetailsCommand.setRole(Role.STAFF.name());
        updateStaffDetailsCommand.setSecurityCredentials(VALID_SECURITY_CREDENTIALS);
        return updateStaffDetailsCommand;
    }

    private Employee generateEmployee() {
        return new Employee(VALID_ID, VALID_FULL_NAME, VALID_ADDRESS, Role.MANAGER, VALID_SECURITY_CREDENTIALS);
    }


}