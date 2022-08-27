package com.example.employeebc.employee.application.manager;

import com.example.employeebc.employee.application.manager.interfaces.IManagerJpaToManagerMapper;
import com.example.employeebc.employee.application.manager.interfaces.IManagerRepository;
import com.example.employeebc.employee.application.manager.interfaces.IManagerToManagerJpaMapper;
import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.domain.manager.Manager;
import com.example.employeebc.employee.domain.manager.interfaces.*;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerApplicationServiceTests {

    @InjectMocks
    private ManagerApplicationService classUnderTest;

    @Mock
    private IManagerRepository mockManagerRepository;

    @Mock
    private IStaffRepository mockStaffRepository;

    @Mock
    private IManagerJpaToManagerMapper mockManagerJpaToManagerMapper;

    @Mock
    private IManagerToManagerJpaMapper mockManagerToManagerJpaMapper;

    @Mock
    private JmsTemplate mockJmsTemplate;

    @Mock
    private ObjectMapper mockObjectMapper;

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
    @DisplayName("When given valid ids then the staff is added to the team")
    void test01(){

        Optional<ManagerJpa> managerJpa = generateValidManagerJpa();
        Optional<StaffJpa> staffJpa = generateValidStaffJpa();
        Manager manager  = generateValidManager();

        Mockito.when(mockManagerRepository.findById(Mockito.anyString())).thenReturn(managerJpa);
        Mockito.when(mockStaffRepository.findById(Mockito.anyString())).thenReturn(staffJpa);
        Mockito.when(mockManagerJpaToManagerMapper.map(managerJpa.get())).thenReturn(manager);
        Mockito.when(mockManagerToManagerJpaMapper.map(manager)).thenReturn(managerJpa.get());

        classUnderTest.addStaffToManagerTeam("123", "123");

        Mockito.verify(mockManagerRepository, Mockito.times(1)).save(managerJpa.get());

    }

    @Test
    @DisplayName("When given a manager id, it is not found, and an IllegalArgumentException is thrown")
    void test02() {

        Optional<ManagerJpa> managerJpa = Optional.empty();
        Mockito.when(mockManagerRepository.findById(Mockito.anyString())).thenReturn(managerJpa);

        assertThrows(IllegalArgumentException.class, () -> {
            classUnderTest.addStaffToManagerTeam("123", "123");
        });

    }

    @Test
    @DisplayName("When given a staff id, it is not found, and an IllegalArgumentException is thrown")
    void test03() {

        Optional<StaffJpa> staffJpa = Optional.empty();
        Mockito.when(mockStaffRepository.findById(Mockito.anyString())).thenReturn(staffJpa);

        assertThrows(IllegalArgumentException.class, () -> {
            classUnderTest.addStaffToManagerTeam("123", "123");
        });

    }

    @Test
    @DisplayName("When given a valid ICreateSkillCommand command then a message will be sent to the queue")
    void test04() throws JsonProcessingException {
        ICreateSkillCommand mockCreateSkillCommand = Mockito.mock(ICreateSkillCommand.class);
        String eventToJson = "String";

        Mockito.when(mockCreateSkillCommand.getDescription()).thenReturn("test");
        Mockito.when(mockCreateSkillCommand.getCategoryId()).thenReturn("1");
        Mockito.when(mockObjectMapper.writeValueAsString(Mockito.any())).thenReturn(eventToJson);

        classUnderTest.createSkill(mockCreateSkillCommand);

        Mockito.verify(mockJmsTemplate, Mockito.times(1)).convertAndSend("SKILL.CREATE.QUEUE", eventToJson);
    }

    @Test
    @DisplayName("When given a valid IEditSkillCommand command then a message will be sent to the queue")
    void test05() throws JsonProcessingException {
        IEditSkillCommand mockIEditSkillCommand = Mockito.mock(IEditSkillCommand.class);
        String eventToJson = "String";

        Mockito.when(mockIEditSkillCommand.getDescription()).thenReturn("test");
        Mockito.when(mockIEditSkillCommand.getSkillId()).thenReturn("1");
        Mockito.when(mockObjectMapper.writeValueAsString(Mockito.any())).thenReturn(eventToJson);

        classUnderTest.editSkill(mockIEditSkillCommand);

        Mockito.verify(mockJmsTemplate, Mockito.times(1)).convertAndSend("SKILL.EDIT.QUEUE", eventToJson);
    }

    @Test
    @DisplayName("When given a valid IDeleteSkillCommand command then a message will be sent to the queue")
    void test06() throws JsonProcessingException {
        IDeleteSkillCommand mockIDeleteSkillCommand = Mockito.mock(IDeleteSkillCommand.class);
        String eventToJson = "String";

        Mockito.when(mockIDeleteSkillCommand.getSkillId()).thenReturn("1");
        Mockito.when(mockObjectMapper.writeValueAsString(Mockito.any())).thenReturn(eventToJson);

        classUnderTest.deleteSkill(mockIDeleteSkillCommand);

        Mockito.verify(mockJmsTemplate, Mockito.times(1)).convertAndSend("EMPLOYEE.DELETE.SKILL.QUEUE", eventToJson);
    }

    @Test
    @DisplayName("When given a valid ICreateCategoryCommand command then a message will be sent to the queue")
    void test07() throws JsonProcessingException {
        ICreateCategoryCommand mockICreateCategoryCommand = Mockito.mock(ICreateCategoryCommand.class);
        String eventToJson = "String";

        Mockito.when(mockICreateCategoryCommand.getDescription()).thenReturn("test");
        Mockito.when(mockObjectMapper.writeValueAsString(Mockito.any())).thenReturn(eventToJson);

        classUnderTest.createCategory(mockICreateCategoryCommand);

        Mockito.verify(mockJmsTemplate, Mockito.times(1)).convertAndSend("CATEGORY.CREATE.QUEUE", eventToJson);
    }

    @Test
    @DisplayName("When given a valid IEditCategoryCommand command then a message will be sent to the queue")
    void test08() throws JsonProcessingException {
        IEditCategoryCommand mockIEditCategoryCommand = Mockito.mock(IEditCategoryCommand.class);
        String eventToJson = "String";

        Mockito.when(mockIEditCategoryCommand.getDescription()).thenReturn("test");
        Mockito.when(mockIEditCategoryCommand.getCategoryId()).thenReturn("1");
        Mockito.when(mockObjectMapper.writeValueAsString(Mockito.any())).thenReturn(eventToJson);

        classUnderTest.editCategory(mockIEditCategoryCommand);

        Mockito.verify(mockJmsTemplate, Mockito.times(1)).convertAndSend("CATEGORY.EDIT.QUEUE", eventToJson);
    }

    @Test
    @DisplayName("When given a valid IDeleteCategoryCommand command then a message will be sent to the queue")
    void test09() throws JsonProcessingException {
        IDeleteCategoryCommand mockIDeleteCategoryCommand = Mockito.mock(IDeleteCategoryCommand.class);
        String eventToJson = "String";

        Mockito.when(mockIDeleteCategoryCommand.getCategoryId()).thenReturn("1");
        Mockito.when(mockObjectMapper.writeValueAsString(Mockito.any())).thenReturn(eventToJson);

        classUnderTest.deleteCategory(mockIDeleteCategoryCommand);

        Mockito.verify(mockJmsTemplate, Mockito.times(1)).convertAndSend("SKILL.CATEGORY.DELETE.QUEUE", eventToJson);
    }


    private Manager generateValidManager() {
        return new Manager(VALID_ID,
                new FullName(VALID_FIRSTNAME, VALID_SURNAME),
                new Address(VALID_HOUSENUMBER, VALID_STREETNAME, VALID_POSTCODE),
                Role.valueOf(ROLE),
                new SecurityCredentials(VALID_SECURITY_CREDENTIALS_USER, VALID_SECURITY_CREDENTIALS_PASS));
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