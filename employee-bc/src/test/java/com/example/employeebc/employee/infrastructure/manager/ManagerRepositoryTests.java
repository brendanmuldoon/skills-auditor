package com.example.employeebc.employee.infrastructure.manager;

import com.example.employeebc.employee.domain.common.Identity;
import com.example.employeebc.employee.domain.common.UniqueIDFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ManagerRepositoryTests {

    @InjectMocks
    private ManagerRepository classUnderTest;

    @Mock
    private CrudManagerRepository crudManagerRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    private final Identity VALID_ID = UniqueIDFactory.createID();
    private final String VALID_FIRSTNAME = "Test";
    private final String VALID_SURNAME = "Test";
    private final String VALID_HOUSENUMBER = "1";
    private final String VALID_STREETNAME = "test";
    private final String VALID_POSTCODE = "test";
    private final String ROLE = "STAFF";
    private final String VALID_SECURITY_CREDENTIALS_USER = "user";
    private final String VALID_SECURITY_CREDENTIALS_PASS = "pass";

    @Test
    void test01() {
        Mockito.when(crudManagerRepository.findAll()).thenReturn(null);
        classUnderTest.findAll();
        Mockito.verify(crudManagerRepository, Mockito.times(1)).findAll();
    }

    @Test
    void test02(){
        Mockito.when(crudManagerRepository.findById(Mockito.anyString())).thenReturn(null);
        classUnderTest.findById("1");
        Mockito.verify(crudManagerRepository, Mockito.times(1)).findById(Mockito.anyString());
    }

    @Test
    void test03() {
        Optional<ManagerJpa> managerJpa = generateValidManagerJpa();
        classUnderTest.save(managerJpa.get());
        Mockito.verify(crudManagerRepository, Mockito.times(1)).save(Mockito.any());

    }

    private Optional<ManagerJpa> generateValidManagerJpa() {
        return Optional.of(new ManagerJpa(
                VALID_ID.id(),
                VALID_FIRSTNAME,
                VALID_SURNAME,
                VALID_HOUSENUMBER,
                VALID_STREETNAME,
                VALID_POSTCODE,
                ROLE,
                VALID_SECURITY_CREDENTIALS_USER,
                VALID_SECURITY_CREDENTIALS_PASS
        ));
    }
}