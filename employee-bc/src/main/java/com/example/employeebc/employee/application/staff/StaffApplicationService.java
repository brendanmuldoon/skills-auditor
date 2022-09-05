package com.example.employeebc.employee.application.staff;

import com.example.employeebc.employee.application.manager.events.EmployeeDeleteSkillEvent;
import com.example.employeebc.employee.application.staff.interfaces.IStaffJpaToStaffMapper;
import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
import com.example.employeebc.employee.application.staff.interfaces.IStaffToStaffJpaMapper;
import com.example.employeebc.employee.domain.staff.Staff;
import com.example.employeebc.employee.domain.staff.StaffSkill;
import com.example.employeebc.employee.domain.staff.StrengthOfSkill;
import com.example.employeebc.employee.domain.staff.interfaces.IAddStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IRemoveStaffSkillCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffDetailsCommand;
import com.example.employeebc.employee.domain.staff.interfaces.IUpdateStaffSkillCommand;
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

import static com.example.employeebc.ApplicationConstants.NO_SKILL_ERROR_MSG;
import static com.example.employeebc.ApplicationConstants.STAFF_ID_NOT_RECOGNISED_ERROR_MSG;

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
        if(staffJpa.isPresent()) {
            Staff staff = staffJpaToStaffMapper.map(staffJpa.get());
            if(staff.hasSkill(removeSkillCommand.getSkillId())) {
                staff.removeASkill(removeSkillCommand.getSkillId());
                StaffJpa updatedStaff = staffToStaffJpaMapper.map(staff);
                staffRepository.save(updatedStaff);
            } else {
                throw new IllegalArgumentException(NO_SKILL_ERROR_MSG);
            }
        } else {
            throw new IllegalArgumentException(STAFF_ID_NOT_RECOGNISED_ERROR_MSG);
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
            throw new IllegalArgumentException(STAFF_ID_NOT_RECOGNISED_ERROR_MSG);
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
            throw new IllegalArgumentException(STAFF_ID_NOT_RECOGNISED_ERROR_MSG);
        }
    }

    @Override
    public void updateStaffSkill(IUpdateStaffSkillCommand updateStaffSkillCommand) {
        Optional<StaffJpa> staffJpa = staffRepository.findById(updateStaffSkillCommand.getStaffId());
        if(staffJpa.isPresent()) {
            Staff staff = staffJpaToStaffMapper.map(staffJpa.get());

            if(staff.hasSkill(updateStaffSkillCommand.getSkillId())) {
                staff.updateAStaffSkill(updateStaffSkillCommand);
                StaffJpa updatedStaffJpa = staffToStaffJpaMapper.map(staff);
                staffRepository.save(updatedStaffJpa);
            } else {
                throw new IllegalArgumentException(NO_SKILL_ERROR_MSG);
            }
        } else {
            throw new IllegalArgumentException(STAFF_ID_NOT_RECOGNISED_ERROR_MSG);
        }
    }


    @JmsListener(destination = "EMPLOYEE.DELETE.SKILL.QUEUE")
    public void deleteSkillListener(Message message) { // validation method to ensure skill is not deleted if it is in use

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

                        if (staff.hasSkill(event.getId())) {

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
