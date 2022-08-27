package com.example.employeebc.employee.application.staff;

import com.example.employeebc.employee.application.staff.dto.StaffDTO;
import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
import com.example.employeebc.employee.domain.common.Identity;
import com.example.employeebc.employee.domain.common.UniqueIDFactory;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffQueryHandlerTests {

    @InjectMocks
    private StaffQueryHandler classUnderTest;

    @Mock
    private IStaffRepository mockStaffRepository;

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

    @ParameterizedTest
    @ValueSource(ints = {5, 0})
    void test01(int count) {
        Iterable<StaffJpa> staffJpas = generateValidStaffJpas(count);

        Mockito.when(mockStaffRepository.findAllWithExpiredSkills()).thenReturn(staffJpas);

        List<StaffDTO> result = classUnderTest.findAllWithExpiredSkills();

        assertEquals(count, result.size());
    }


    private Iterable<StaffJpa> generateValidStaffJpas(int count) {
        List<StaffJpa> staffJpas = new ArrayList<>();

        for(int i = 0 ; i < count ; i++) {
            staffJpas.add(generateValidStaffJpa(count));
        }


        return staffJpas;
    }

    private StaffJpa generateValidStaffJpa(int count) {
        return new StaffJpa(
                VALID_ID.id(),
                VALID_FIRSTNAME+count,
                VALID_SURNAME,
                VALID_HOUSENUMBER+count,
                VALID_STREETNAME,
                VALID_POSTCODE,
                ROLE,
                VALID_SECURITY_CREDENTIALS_USER,
                VALID_SECURITY_CREDENTIALS_PASS
        );
    }


}