package com.example.employeebc.employee.application.staff;

import com.example.employeebc.employee.application.manager.events.EmployeeDeleteSkillEvent;
import com.example.employeebc.employee.application.staff.dto.StaffDTO;
import com.example.employeebc.employee.application.staff.interfaces.IStaffJpaToStaffMapper;
import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
import com.example.employeebc.employee.application.staff.interfaces.IStaffToStaffJpaMapper;
import com.example.employeebc.employee.application.staff.mappers.StaffJpaToDTOMapper;
import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.domain.staff.ExpirationDate;
import com.example.employeebc.employee.domain.staff.Staff;
import com.example.employeebc.employee.domain.staff.interfaces.IAddStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IRemoveStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffDetailsCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffSkillCommand;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.lang.model.element.ElementVisitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StaffApplicationServiceTests {

    @Mock
    private IStaffRepository staffRepository;
    @Mock
    private IStaffJpaToStaffMapper staffJpaToStaffMapper;
    @Mock
    private IStaffToStaffJpaMapper staffToStaffJpaMapper;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private JmsTemplate jmsTemplate;
    @InjectMocks
    private StaffApplicationService classUnderTest;
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

        IRemoveStaffSkillCommand iRemoveStaffSkillCommand = Mockito.mock(IRemoveStaffSkillCommand.class);

        Optional<StaffJpa> staffJpa = generateValidStaffJpa();
        Staff staff = generateValidStaff();

        Mockito.when(staffRepository.findById(Mockito.anyString())).thenReturn(staffJpa);
        Mockito.when(staffJpaToStaffMapper.map(staffJpa.get())).thenReturn(staff);
        Mockito.when(staffToStaffJpaMapper.map(staff)).thenReturn(staffJpa.get());
        Mockito.when(iRemoveStaffSkillCommand.getStaffId()).thenReturn("123");

        classUnderTest.removeStaffSkill(iRemoveStaffSkillCommand);

        Mockito.verify(staffRepository, Mockito.times(1)).save(staffJpa.get());

    }

    @Test
    void test02(){

        IRemoveStaffSkillCommand iRemoveStaffSkillCommand = Mockito.mock(IRemoveStaffSkillCommand.class);

        assertThrows(IllegalArgumentException.class, () -> {
            classUnderTest.removeStaffSkill(iRemoveStaffSkillCommand);
        });
    }

    @Test
    void test03() {

        Optional<StaffJpa> staffJpa = generateValidStaffJpa();
        Staff staff = generateValidStaff();

        IAddStaffSkillCommand iAddStaffSkillCommand = Mockito.mock(IAddStaffSkillCommand.class);

        Mockito.when(staffRepository.findById(Mockito.anyString())).thenReturn(staffJpa);
        Mockito.when(staffJpaToStaffMapper.map(staffJpa.get())).thenReturn(staff);
        Mockito.when(iAddStaffSkillCommand.getSkillId()).thenReturn("123");
        Mockito.when(iAddStaffSkillCommand.getStrengthOfSkill()).thenReturn("BASIC");
        Mockito.when(iAddStaffSkillCommand.getExpirationDate()).thenReturn(new ExpirationDate(1, 2023));
        Mockito.when(staffToStaffJpaMapper.map(staff)).thenReturn(staffJpa.get());
        Mockito.when(iAddStaffSkillCommand.getStaffId()).thenReturn("123");

        classUnderTest.addStaffSkill(iAddStaffSkillCommand);

        Mockito.verify(staffRepository, Mockito.times(1)).save(staffJpa.get());

    }

    @Test
    void test04() {

        IAddStaffSkillCommand iAddStaffSkillCommand = Mockito.mock(IAddStaffSkillCommand.class);

        assertThrows(IllegalArgumentException.class, () -> {
           classUnderTest.addStaffSkill(iAddStaffSkillCommand);
        });

    }

    @Test
    void test05() {
        IUpdateStaffDetailsCommand updateStaffDetailsCommand = Mockito.mock(IUpdateStaffDetailsCommand.class);
        Optional<StaffJpa> staffJpa = generateValidStaffJpa();
        Staff staff = generateValidStaff();
        Mockito.when(updateStaffDetailsCommand.getStaffId()).thenReturn("123");
        Mockito.when(staffRepository.findById(Mockito.anyString())).thenReturn(staffJpa);
        Mockito.when(staffJpaToStaffMapper.map(staffJpa.get())).thenReturn(staff);
        Mockito.when(updateStaffDetailsCommand.getFullName()).thenReturn(new FullName(VALID_FIRSTNAME, VALID_SURNAME));
        Mockito.when(updateStaffDetailsCommand.getAddress()).thenReturn(new Address(VALID_HOUSENUMBER, VALID_STREETNAME, VALID_POSTCODE));
        Mockito.when(updateStaffDetailsCommand.getRole()).thenReturn(ROLE);
        Mockito.when(updateStaffDetailsCommand.getSecurityCredentials()).thenReturn(new SecurityCredentials(VALID_SECURITY_CREDENTIALS_USER, VALID_SECURITY_CREDENTIALS_PASS));

        classUnderTest.updateStaffDetails(updateStaffDetailsCommand);

        Mockito.verify(staffRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void test06() {

        IUpdateStaffDetailsCommand iUpdateStaffDetailsCommand = Mockito.mock(IUpdateStaffDetailsCommand.class);

        assertThrows(IllegalArgumentException.class, () -> {
            classUnderTest.updateStaffDetails(iUpdateStaffDetailsCommand);
        });

    }

    @Test
    void test07() {
        IUpdateStaffSkillCommand updateStaffSkillCommand = Mockito.mock(IUpdateStaffSkillCommand.class);
        Optional<StaffJpa> staffJpa = generateValidStaffJpa();
        Staff staff = generateValidStaff();
        Mockito.when(updateStaffSkillCommand.getStaffId()).thenReturn("123");
        Mockito.when(staffRepository.findById(Mockito.anyString())).thenReturn(staffJpa);
        Mockito.when(staffJpaToStaffMapper.map(staffJpa.get())).thenReturn(staff);
        Mockito.when(staffToStaffJpaMapper.map(staff)).thenReturn(staffJpa.get());

        classUnderTest.updateStaffSkill(updateStaffSkillCommand);

        Mockito.verify(staffRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void test08() {
        IUpdateStaffSkillCommand updateStaffSkillCommand = Mockito.mock(IUpdateStaffSkillCommand.class);

        assertThrows(IllegalArgumentException.class, () -> {
           classUnderTest.updateStaffSkill(updateStaffSkillCommand);
        });
    }

    @Test
    void test09() throws JMSException, JsonProcessingException {
        TextMessage textMessage = Mockito.mock(TextMessage.class);
        EmployeeDeleteSkillEvent event = new EmployeeDeleteSkillEvent();
        event.setId("123");
        Iterable<StaffJpa> result = generateValidStaffJpas(5);
        String text = "test";
        Staff staff = generateValidStaff();

        Mockito.when(staffJpaToStaffMapper.map(Mockito.any())).thenReturn(staff);
        Mockito.when(textMessage.getText()).thenReturn(text);
        Mockito.when(objectMapper.readValue(text, EmployeeDeleteSkillEvent.class)).thenReturn(event);
        Mockito.when(staffRepository.findAll()).thenReturn(result);
        Mockito.when(objectMapper.writeValueAsString(Mockito.any(EmployeeDeleteSkillEvent.class))).thenReturn(text);

        classUnderTest.deleteSkillListener(textMessage);

        Mockito.verify(jmsTemplate, Mockito.times(1)).convertAndSend("SKILL.DELETE.QUEUE", text);
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

    private Staff generateValidStaff() {
        return new Staff(VALID_ID,
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
}