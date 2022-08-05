package com.example.employeebc.employee.application.staff;

import com.example.employeebc.employee.application.manager.events.EmployeeDeleteSkillEvent;
import com.example.employeebc.employee.application.staff.interfaces.*;
import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.domain.staff.Staff;
import com.example.employeebc.employee.domain.staff.StaffSkill;
import com.example.employeebc.employee.domain.staff.StrengthOfSkill;
import com.example.employeebc.employee.domain.staff.interfaces.*;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.example.employeebc.employee.ui.staff.IStaffApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class StaffApplicationService implements IStaffApplicationService {

    private IStaffRepository staffRepository;
    private IStaffJpaToStaffMapper staffJpaToStaffMapper;
    private IStaffToStaffJpaMapper staffToStaffJpaMapper;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper;

    private JmsTemplate jmsTemplate;




    @Override
    public void removeStaffSkill(IRemoveStaffSkillCommand removeSkillCommand) {
        Optional<StaffJpa> staffJpa = staffRepository.findById(removeSkillCommand.getStaffId());
        if(staffJpa.isPresent()) { // need to validate that the staff has the skill before deleting
            Staff staff = staffJpaToStaffMapper.map(staffJpa.get());
            staff.removeASkill(removeSkillCommand.getSkillId());
            StaffJpa updatedStaff = staffToStaffJpaMapper.map(staff);
            staffRepository.save(updatedStaff);
        } else {
            throw new IllegalArgumentException("Staff id is not recognised");
        }
    }

    @Override
    public void addStaffSkill(IAddStaffSkillCommand addStaffSkillCommand) {
        Optional<StaffJpa> staffJpa = staffRepository.findById(addStaffSkillCommand.getStaffId());

        if(staffJpa.isPresent()) {

            Staff staff = staffJpaToStaffMapper.map(staffJpa.get());

            StaffSkill staffSkill = new StaffSkill(
                    addStaffSkillCommand.getSkillId(),
                    StrengthOfSkill.valueOf(addStaffSkillCommand.getStrengthOfSkill()),
                    addStaffSkillCommand.getExpirationDate());

            staff.addSkill(staffSkill);

            staffRepository.save(staffToStaffJpaMapper.map(staff));

        } else {
            throw new IllegalArgumentException("Staff id is not recognised");
        }
    }

    @Override
    public void updateStaffDetails(IUpdateStaffDetailsCommand updateStaffDetailsCommand) {
        Optional<StaffJpa> staffJpa = staffRepository.findById(updateStaffDetailsCommand.getStaffId());
        if(staffJpa.isPresent()) {
            Staff staff = staffJpaToStaffMapper.map(staffJpa.get());
            staff.updateStaffDetails(updateStaffDetailsCommand);
            staffRepository.save(staffToStaffJpaMapper.map(staff));
        } else {
            throw new IllegalArgumentException("Staff id is not recognised");
        }
    }

    @Override
    public void deleteStaff(IDeleteStaffCommand deleteStaffCommand) {
        Optional<StaffJpa> staffJpa = staffRepository.findById(deleteStaffCommand.getStaffId());
        if(staffJpa.isPresent()) {
            staffRepository.delete(staffJpa.get());
        } else {
            throw new IllegalArgumentException("Staff id is not recognised");
        }
    }

    @Override
    public void updateStaffSkill(IUpdateStaffSkillCommand updateStaffSkillCommand) {
        Optional<StaffJpa> staffJpa = staffRepository.findById(updateStaffSkillCommand.getStaffId());
        if(staffJpa.isPresent()) {
            Staff staff = staffJpaToStaffMapper.map(staffJpa.get());
            staff.updateAStaffSkill(updateStaffSkillCommand);
            StaffJpa updatedStaffJpa = staffToStaffJpaMapper.map(staff);
            staffRepository.save(updatedStaffJpa);
        } else {
            throw new IllegalArgumentException("Staff id is not recognised");
        }
    }

    @Override
    public void createStaff(ICreateStaffCommand createStaffCommand) {
        Identity identity = UniqueIDFactory.createID();
        FullName fullName = new FullName(createStaffCommand.getFirstName(), createStaffCommand.getSurname());
        Address address = new Address(createStaffCommand.getHouseNumber(), createStaffCommand.getStreetName(), createStaffCommand.getPostcode());
        Role role = Role.valueOf(createStaffCommand.getRole().toUpperCase());
        SecurityCredentials securityCredentials = new SecurityCredentials(createStaffCommand.getUsername(), createStaffCommand.getPassword());
        Staff staff = Staff.staffOf(identity, fullName, address, role, securityCredentials);
        staffRepository.save(staffToStaffJpaMapper.map(staff));
    }

    @JmsListener(destination = "EMPLOYEE.DELETE.SKILL.QUEUE")
    public void deleteSkillListener(Message message) {

        LOG.info("Received message from EMPLOYEE.DELETE.SKILL.QUEUE");

        try {

            if(message instanceof TextMessage) {

                String messageBody = ((TextMessage) message).getText();

                EmployeeDeleteSkillEvent event = objectMapper.readValue(messageBody, EmployeeDeleteSkillEvent.class);

                Iterable<StaffJpa> staffWithSkills = staffRepository.findAll();

                Staff staff;

                boolean inUse = false;

                if( staffWithSkills != null) {
                    for(StaffJpa s : staffWithSkills) {

                        staff = staffJpaToStaffMapper.map(s);

                        if (staff.retrieveSkillById(event.getId())) {

                            inUse = true;

                            break;

                        }
                    }
                }

                if(inUse) {

                    LOG.info("Cannot delete, Skill is in use");

                } else {

                    String eventToJson = objectMapper.writeValueAsString(event);

                    jmsTemplate.convertAndSend("SKILL.DELETE.QUEUE", eventToJson);

                }

            }

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }
}
