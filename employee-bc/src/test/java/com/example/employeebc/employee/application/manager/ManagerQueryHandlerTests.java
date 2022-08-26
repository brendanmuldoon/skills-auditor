package com.example.employeebc.employee.application.manager;

import com.example.employeebc.employee.application.manager.dto.EmployeeSkillDTO;
import com.example.employeebc.employee.application.manager.dto.EmployeeSkillDTOList;
import com.example.employeebc.employee.application.manager.dto.ManagerTeamDTO;
import com.example.employeebc.employee.application.manager.interfaces.IManagerJpaToManagerMapper;
import com.example.employeebc.employee.application.manager.interfaces.IManagerRepository;
import com.example.employeebc.employee.application.staff.dto.StaffDTO;
import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTOTests;
import com.example.employeebc.employee.domain.manager.Manager;
import com.example.employeebc.employee.domain.manager.ManagerTeam;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import com.example.employeebc.employee.infrastructure.manager.ManagerTeamJpaValueObject;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.example.employeebc.employee.infrastructure.staff.StaffSkillJpaValueObject;
import com.example.employeebc.employee.ui.staff.IStaffQueryHandler;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class ManagerQueryHandlerTests {

    @InjectMocks
    private ManagerQueryHandler classUnderTest;

    @Mock
    private IManagerRepository mockManagerRepository;

    @Mock
    private IStaffQueryHandler mockStaffQueryHandler;

    @Mock
    private IManagerJpaToManagerMapper mockManagerJpaToManagerMapper;

    @Mock
    private RestTemplate mockRestTemplate;

    @BeforeEach
    public void setup(){
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
    @DisplayName("When given a valid id, then a valid manager team is returned")
    void test01() {
        Optional<ManagerJpa> managerJpa = generateValidManagerJpa();
        Manager manager = generateValidManager();
        manager.addTeamMember(new ManagerTeam("1", "2", new FullName("test", "test")));

        Mockito.when(mockManagerRepository.findById(Mockito.anyString())).thenReturn(managerJpa);
        Mockito.when(mockManagerJpaToManagerMapper.map(managerJpa.get())).thenReturn(manager);

        List<ManagerTeam> result = classUnderTest.findTeamByManagerId("123");

        assertEquals(1, result.size());

    }

    @Test
    @DisplayName("When given an invalid is, then an empty array list is returned")
    void test02() {
        Optional<ManagerJpa> managerJpa = Optional.empty();

        Mockito.when(mockManagerRepository.findById(Mockito.anyString())).thenReturn(managerJpa);

        List<ManagerTeam> result = classUnderTest.findTeamByManagerId("123");

        assertEquals(0, result.size());

    }

    @Test
    @DisplayName("When given a valid managerid and skillid, then a list of ManagerTeamDTOs will be returned")
    void test03() {
        int count = 5;
        Optional<ManagerJpa> managerJpa = generateValidManagerJpa();
        List<ManagerTeamJpaValueObject> team = generateValidTeamJpaObjects(count);
        managerJpa.get().setTeam(team);
        Mockito.when(mockManagerRepository.findById(Mockito.anyString())).thenReturn(managerJpa);

        List<ManagerTeamDTO> result = classUnderTest.findTeamBySkillId("1", "1");

        assertEquals(count, result.size());

    }

    @Test
    @DisplayName("When given a valid managerid and invalid skillid, then an empty list of ManagerTeamDTOs will be returned")
    void test04() {

        Optional<ManagerJpa> managerJpa = generateValidManagerJpa();
        Mockito.when(mockManagerRepository.findById(Mockito.anyString())).thenReturn(managerJpa);

        List<ManagerTeamDTO> result = classUnderTest.findTeamBySkillId("1", "2");

        assertEquals(0, result.size());

    }

    @Test
    @DisplayName("When given valid category id then a valid employeeskilldtolist is returned with 5 results")
    void test05() {

        EmployeeSkillDTOList employeeSkillDTOList = generateEmployeeSkillDTOList();

        Mockito.when(mockRestTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(employeeSkillDTOList);

        EmployeeSkillDTOList result = classUnderTest.findSkillsByCategory("1");

        assertEquals(5, result.getSkills().size());
    }

    @Test
    @DisplayName("When given an invalid category id then a valid employeeskilldtolist is returned with 0 results")
    void test06() {

        EmployeeSkillDTOList employeeSkillDTOList = new EmployeeSkillDTOList();

        Mockito.when(mockRestTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(employeeSkillDTOList);

        EmployeeSkillDTOList result = classUnderTest.findSkillsByCategory("1");

        assertEquals(0, result.getSkills().size());
    }

    @Test
    @DisplayName("When there are staff with expired skills then a list of staff dtos is returned")
    void test07() {
        int count = 5;
        List<StaffDTO> staffDTOS = generateValidStaffDtos(count);
        Mockito.when(mockStaffQueryHandler.findAllWithExpiredSkills()).thenReturn(staffDTOS);

        List<?> result = classUnderTest.findAllWithExpiredSkills();

        assertEquals(count, result.size());

    }

    @Test
    @DisplayName("When there are no staff with expired skills then an empty list is returned")
    void test08() {
        int count = 0;
        List<StaffDTO> staffDTOS = generateValidStaffDtos(count);
        Mockito.when(mockStaffQueryHandler.findAllWithExpiredSkills()).thenReturn(staffDTOS);

        List<?> result = classUnderTest.findAllWithExpiredSkills();

        assertEquals(count, result.size());

    }

    private List<StaffDTO> generateValidStaffDtos(int count) {
        List<StaffDTO> staffDTOS = new ArrayList<>();
        for( int i = 0; i<count; i++) {
            staffDTOS.add(
                    new StaffDTO(
                            String.valueOf(i),
                            "test",
                            "test",
                            String.valueOf(i),
                            "test",
                            "test",
                            Role.STAFF,
                            "test",
                            "test",
                            new ArrayList<>()
                    )
            );
        }
        return staffDTOS;
    }

    private EmployeeSkillDTOList generateEmployeeSkillDTOList() {
        EmployeeSkillDTOList employeeSkillDTOList = new EmployeeSkillDTOList();

        for (int i = 0 ; i < 5; i++) {
            employeeSkillDTOList.getSkills().add(generateEmployeeSkillDTO(i));
        }

        return employeeSkillDTOList;

    }

    private EmployeeSkillDTO generateEmployeeSkillDTO(int i) {
        EmployeeSkillDTO dto = new EmployeeSkillDTO();
        dto.setId(String.valueOf(i));
        dto.setDescription("desc"+i);
        return dto;
    }

    private List<ManagerTeamJpaValueObject> generateValidTeamJpaObjects(int count) {
        List<ManagerTeamJpaValueObject> team = new ArrayList<>();
        for (int i = 0; i< count; i++) {
            StaffJpa staffJpa = new StaffJpa(
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

            staffJpa.addSkill(
                    new StaffSkillJpaValueObject(
                            Long.parseLong("1"),
                            "1",
                            "BASIC",
                            LocalDate.of(2023, 12, 1)

                    )
            );

            team.add(new ManagerTeamJpaValueObject(Long.parseLong("1"),
                    staffJpa,
                    "test:"+i, "test:"+i, "test:"+i));
        }
        return team;
    }

    private List<ManagerTeamDTO> generateValidManagerTeamDto(int count) {
        List<ManagerTeamDTO> team = new ArrayList<>();
        for (int i = 0; i< count; i++) {
            team.add(new ManagerTeamDTO(""+i, "test:"+i, "test:"+i, new ArrayList<>()));
        }
        return team;
    }

    private Manager generateValidManager() {
        return new Manager(VALID_ID,
                new FullName(VALID_FIRSTNAME, VALID_SURNAME),
                new Address(VALID_HOUSENUMBER, VALID_STREETNAME, VALID_POSTCODE),
                Role.valueOf(ROLE),
                new SecurityCredentials(VALID_SECURITY_CREDENTIALS_USER, VALID_SECURITY_CREDENTIALS_PASS));
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