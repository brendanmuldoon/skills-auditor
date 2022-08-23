package com.example.employeebc.employee.domain.manager;

import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerToManagerJpaMapperTests {

    private ManagerToManagerJpaMapper classUnderTest;

    private final Identity VALID_ID = UniqueIDFactory.createID();
    private final String VALID_FIRSTNAME = "Test";
    private final String VALID_SURNAME = "Test";
    private final String VALID_HOUSENUMBER = "1";
    private final String VALID_STREETNAME = "test";
    private final String VALID_POSTCODE = "test";
    private final String ROLE = "STAFF";
    private final String VALID_SECURITY_CREDENTIALS_USER = "user";
    private final String VALID_SECURITY_CREDENTIALS_PASS = "pass";

    private Manager manager;

    @BeforeEach
    void init() {
        manager = new Manager(
                VALID_ID,
                new FullName(VALID_FIRSTNAME, VALID_SURNAME),
                new Address(VALID_HOUSENUMBER, VALID_STREETNAME, VALID_POSTCODE),
                Role.valueOf(ROLE),
                new SecurityCredentials(VALID_SECURITY_CREDENTIALS_USER, VALID_SECURITY_CREDENTIALS_PASS)
        );

        classUnderTest = new ManagerToManagerJpaMapper();
    }

    @Test
    @DisplayName("When provided with a valid Manager a valid ManagerJpa object is created")
    void test01() {

        assertDoesNotThrow(() -> {
            classUnderTest.map(manager);
        });

    }

    @Test
    @DisplayName("When provided with a valid Manager a valid ManagerJpa object is created and the information is the same")
    void test02() {

        ManagerJpa managerJpa = classUnderTest.map(manager);
        assertEquals(VALID_ID.id(), managerJpa.getId());
        assertEquals(VALID_FIRSTNAME, managerJpa.getFullname_firstname());
        assertEquals(VALID_SURNAME, managerJpa.getFullname_surname());
        assertEquals(VALID_HOUSENUMBER, managerJpa.getAddress_housenumber());
        assertEquals(VALID_STREETNAME, managerJpa.getAddress_streetname());
        assertEquals(VALID_POSTCODE, managerJpa.getAddress_postcode());
        assertEquals(ROLE, managerJpa.getRole().toUpperCase());
        assertEquals(VALID_SECURITY_CREDENTIALS_USER, managerJpa.getSecuritycredentials_username());
        assertEquals(VALID_SECURITY_CREDENTIALS_PASS, managerJpa.getSecuritycedentials_password());
        
    }

}