package com.example.employeebc.employee.infrastructure.staff;

import com.example.employeebc.employee.domain.common.Identity;
import com.example.employeebc.employee.domain.common.UniqueIDFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

public class StaffRepositoryTests {

    @Mock
    private CrudStaffRepository crudStaffRepository;
    @InjectMocks
    private StaffRepository classUnderTest;

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
    void test01(){
        Mockito.when(crudStaffRepository.findAll()).thenReturn(null);
        classUnderTest.findAll();
        Mockito.verify(crudStaffRepository, Mockito.times(1)).findAll();
    }

    @Test
    void test02(){
        Mockito.when(crudStaffRepository.findById(anyString())).thenReturn(null);
        classUnderTest.findById("12");
        Mockito.verify(crudStaffRepository, Mockito.times(1)).findById("12");
    }

    @Test
    void test03() {
        Optional<StaffJpa> staffJpa = generateValidStaffJpa();
        classUnderTest.save(staffJpa.get());
        Mockito.verify(crudStaffRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void test04()  {
        Mockito.when(crudStaffRepository.findAllWithExpiredSkills()).thenReturn(null);
        classUnderTest.findAllWithExpiredSkills();
        Mockito.verify(crudStaffRepository, Mockito.times(1)).findAllWithExpiredSkills();
    }

    private Optional<StaffJpa> generateValidStaffJpa() {
        return Optional.of(new StaffJpa(
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