package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.application.manager.interfaces.IManagerJpaToManagerMapper;
import com.example.employeebc.employee.domain.common.Identity;
import com.example.employeebc.employee.domain.common.Role;
import com.example.employeebc.employee.domain.common.UniqueIDFactory;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerJpaToManagerMapperTests {

    private ManagerJpa managerJpa;

    private IManagerJpaToManagerMapper classUnderTest;

    private final Identity VALID_ID = UniqueIDFactory.createID();
    private final String VALID_FIRSTNAME = "Test";
    private final String VALID_SURNAME = "Test";
    private final String VALID_HOUSENUMBER = "1";
    private final String VALID_STREETNAME = "test";
    private final String VALID_POSTCODE = "test";
    private final String ROLE = "STAFF";
    private final String VALID_SECURITY_CREDENTIALS_USER = "user";
    private final String VALID_SECURITY_CREDENTIALS_PASS = "pass";

    @BeforeEach
    void init() {
        managerJpa = new ManagerJpa(
                VALID_ID.id(),
                VALID_FIRSTNAME,
                VALID_SURNAME,
                VALID_HOUSENUMBER,
                VALID_STREETNAME,
                VALID_POSTCODE,
                ROLE,
                VALID_SECURITY_CREDENTIALS_USER,
                VALID_SECURITY_CREDENTIALS_PASS
        );

        classUnderTest = new ManagerJpaToManagerMapper();
    }

    @Test
    @DisplayName("When provided with a valid ManagerJpa a valid Manager object is created")
    void test01() {

        assertDoesNotThrow(() -> {
            classUnderTest.map(managerJpa);
        });
    }

    @Test
    @DisplayName("When provided with a valid ManagerJpa a valid Manager object is created and the information is the same")
    void test02() {
        Manager manager = classUnderTest.map(managerJpa);
        assertEquals(VALID_ID.toString(), manager.id().toString());
        assertEquals(VALID_FIRSTNAME, manager.fullName().getFirstName());
        assertEquals(VALID_SURNAME, manager.fullName().surname());
        assertEquals(VALID_HOUSENUMBER, manager.address().houseNumber());
        assertEquals(VALID_STREETNAME, manager.address().streetName());
        assertEquals(VALID_POSTCODE, manager.address().postcode());
        assertEquals(ROLE, manager.role().getEmployeeRole().toUpperCase());
        assertEquals(VALID_SECURITY_CREDENTIALS_USER, manager.securityCredentials().username());
        assertEquals(VALID_SECURITY_CREDENTIALS_PASS, manager.securityCredentials().password());
    }

}