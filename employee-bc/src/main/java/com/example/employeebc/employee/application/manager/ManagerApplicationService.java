package com.example.employeebc.employee.application.manager;

import com.example.employeebc.ApplicationConstants;
import com.example.employeebc.employee.application.manager.events.*;
import com.example.employeebc.employee.application.manager.interfaces.IManagerJpaToManagerMapper;
import com.example.employeebc.employee.application.manager.interfaces.IManagerRepository;
import com.example.employeebc.employee.application.manager.interfaces.IManagerToManagerJpaMapper;
import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
import com.example.employeebc.employee.domain.common.FullName;
import com.example.employeebc.employee.domain.common.Identity;
import com.example.employeebc.employee.domain.manager.Manager;
import com.example.employeebc.employee.domain.manager.ManagerTeam;
import com.example.employeebc.employee.domain.manager.interfaces.*;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import com.example.employeebc.employee.infrastructure.manager.ManagerTeamJpaValueObject;
import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import com.example.employeebc.employee.ui.manager.IManagerApplicationService;
import com.example.employeebc.employee.domain.common.UniqueIDFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.example.employeebc.ApplicationConstants.MANAGER_ID_NOT_RECOGNISED_ERROR_MSG;
import static com.example.employeebc.ApplicationConstants.STAFF_ID_NOT_RECOGNISED_ERROR_MSG;

@Service
@AllArgsConstructor
@Transactional
public class ManagerApplicationService implements IManagerApplicationService {

    private IManagerRepository managerRepository;

    private IStaffRepository staffRepository;

    private IManagerJpaToManagerMapper managerJpaToManagerMapper;

    private IManagerToManagerJpaMapper managerToManagerJpaMapper;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private JmsTemplate jmsTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void addStaffToManagerTeam(String managerId, String staffId) {
        Optional<ManagerJpa> managerJpa = managerRepository.findById(managerId);
        Optional<StaffJpa> staffJpa = staffRepository.findById(staffId);
        if(managerJpa.isPresent()) {

            if (staffJpa.isPresent()) {

                Manager manager = managerJpaToManagerMapper.map(managerJpa.get());

                ManagerTeam teamMember = ManagerTeam.managerTeamOf(staffId,
                        managerId, new FullName(staffJpa.get().getFullname_firstname(), staffJpa.get().getFullname_surname()));

                manager.addTeamMember(teamMember);

                ManagerJpa managerJpa1 = managerToManagerJpaMapper.map(manager);

                managerJpa1.setTeam(managerJpa.get().getTeam());

                ManagerTeamJpaValueObject newTeamMember = new ManagerTeamJpaValueObject(teamMember.getId(), staffJpa.get(), managerJpa1.getId(), staffJpa.get().getFullname_firstname(), staffJpa.get().getFullname_surname());

                managerJpa1.addTeamMember(newTeamMember);

                managerRepository.save(managerJpa1);

            } else {
                throw new IllegalArgumentException(STAFF_ID_NOT_RECOGNISED_ERROR_MSG);
            }
        } else {
            throw new IllegalArgumentException(MANAGER_ID_NOT_RECOGNISED_ERROR_MSG);
        }
    }

    @Override
    public void createSkill(ICreateSkillCommand createSkillCommand) {

        Identity identity = UniqueIDFactory.createID();

        EmployeeCreateSkillEvent event = new EmployeeCreateSkillEvent();
        event.setId(identity.id());
        event.setDescription(createSkillCommand.getDescription());
        event.setCategory(createSkillCommand.getCategoryId());

        try {
            String eventToJson = objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend(ApplicationConstants.SKILL_CREATE_QUEUE, eventToJson);

        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }

    @Override
    public void editSkill(IEditSkillCommand editSkillCommand) {

        EmployeeEditSkillEvent event = new EmployeeEditSkillEvent();
        event.setId(editSkillCommand.getSkillId());
        event.setDescription(editSkillCommand.getDescription());

        try {
            String eventToJson = objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend(ApplicationConstants.SKILL_EDIT_QUEUE, eventToJson);

        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }

    @Override
    public void deleteSkill(IDeleteSkillCommand deleteSkillCommand) {

            EmployeeDeleteSkillEvent event = new EmployeeDeleteSkillEvent();
            event.setId(deleteSkillCommand.getSkillId());

            try {
                String eventToJson = objectMapper.writeValueAsString(event);

                jmsTemplate.convertAndSend(ApplicationConstants.EMPLOYEE_DELETE_SKILL_QUEUE, eventToJson);

            } catch (JsonProcessingException ex) {
                LOG.error(ex.getMessage());
            }

    }

    @Override
    public void createCategory(ICreateCategoryCommand createCategoryCommand) {

        Identity identity = UniqueIDFactory.createID();

        EmployeeCreateCategoryEvent event = new EmployeeCreateCategoryEvent();
        event.setId(identity.id());
        event.setDescription(createCategoryCommand.getDescription());

        try {
            String eventToJson = objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend(ApplicationConstants.CATEGORY_CREATE_QUEUE, eventToJson);

        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }

    @Override
    public void editCategory(IEditCategoryCommand editCategoryCommand) {

        EmployeeEditCategoryEvent event = new EmployeeEditCategoryEvent();
        event.setId(editCategoryCommand.getCategoryId());
        event.setDescription(editCategoryCommand.getDescription());

        try {
            String eventToJson = objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend(ApplicationConstants.CATEGORY_EDIT_QUEUE, eventToJson);

        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }

    @Override
    public void deleteCategory(IDeleteCategoryCommand editCategoryCommand) {

        EmployeeDeleteCategoryEvent event = new EmployeeDeleteCategoryEvent();
        event.setId(editCategoryCommand.getCategoryId());

        try {
            String eventToJon = objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend(ApplicationConstants.SKILL_CATEGORY_DELETE_QUEUE, eventToJon);
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }


}
