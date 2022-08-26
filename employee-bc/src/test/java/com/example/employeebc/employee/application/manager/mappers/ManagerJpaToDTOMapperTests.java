package com.example.employeebc.employee.application.manager.mappers;

import com.example.employeebc.employee.domain.common.Identity;
import com.example.employeebc.employee.domain.common.UniqueIDFactory;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTO;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import com.example.employeebc.employee.infrastructure.manager.ManagerTeamJpaValueObject;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerJpaToDTOMapperTests {

    private ManagerJpa managerJpa;

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


    }

    @Test
    @DisplayName("When a valid ManagerJpa object is passed then a ManagerTeamDTO list is returned")
    void test01() {
        int count = 5;
        generateTeam(count);
        List<ManagerTeamDTO> team = ManagerJpaToDTOMapper.convertManagerTeamToDTO(managerJpa);
        assertEquals(5, team.size());

    }

    private void generateTeam(int count) {
        for(int i = 0 ; i < count ; i++) {
            managerJpa.addTeamMember(new ManagerTeamJpaValueObject(
                            Long.parseLong("123"),
                            new StaffJpa(VALID_ID.id(),
                                    VALID_FIRSTNAME,
                                    VALID_SURNAME,
                                    VALID_HOUSENUMBER,
                                    VALID_STREETNAME,
                                    VALID_POSTCODE,
                                    ROLE,
                                    VALID_SECURITY_CREDENTIALS_USER,
                                    VALID_SECURITY_CREDENTIALS_PASS
                            ),
                            VALID_ID.id(),
                            VALID_FIRSTNAME,
                            VALID_SURNAME

                    )
            );
        }
    }

}