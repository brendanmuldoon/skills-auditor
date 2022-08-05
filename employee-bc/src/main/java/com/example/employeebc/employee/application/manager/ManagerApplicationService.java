package com.example.employeebc.employee.application.manager;

import com.example.employeebc.employee.application.manager.events.*;
import com.example.employeebc.employee.application.manager.interfaces.IManagerJpaToManagerMapper;
import com.example.employeebc.employee.application.manager.interfaces.IManagerRepository;
import com.example.employeebc.employee.application.manager.interfaces.IManagerToManagerJpaMapper;
import com.example.employeebc.employee.application.staff.interfaces.IStaffRepository;
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
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ManagerApplicationService implements IManagerApplicationService {

    private IManagerRepository managerRepository;

    private IStaffRepository staffRepository;

    private IManagerJpaToManagerMapper managerJpaToManagerMapper;

    private IManagerToManagerJpaMapper managerToManagerJpaMapper;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private ApplicationEventPublisher eventPublisher;

    private JmsTemplate jmsTemplate;

    private ObjectMapper objectMapper;



    private void manageDomainEvents(List<ApplicationEvent> events) {
        for (ApplicationEvent event : events){
            LOG.info("event " + event);
            eventPublisher.publishEvent(event);
            //eventStoreService.append(events);
        }
    }

    @Override
    public void addStaffToManagerTeam(IUpdateManagerTeamCommand updateManagerTeamCommand) {
        Optional<ManagerJpa> managerJpa = managerRepository.findById(updateManagerTeamCommand.getManagerId());
        Optional<StaffJpa> staffJpa = staffRepository.findById(updateManagerTeamCommand.getStaffId());
        if(managerJpa.isPresent()) {

            if (staffJpa.isPresent()) {

                Manager manager = managerJpaToManagerMapper.map(managerJpa.get());

                ManagerTeam teamMember = ManagerTeam.managerTeamOf(updateManagerTeamCommand.getStaffId(),
                        updateManagerTeamCommand.getManagerId());

                manager.addTeamMember(teamMember);

                ManagerJpa managerJpa1 = managerToManagerJpaMapper.map(manager);

                managerJpa1.setTeam(managerJpa.get().getTeam());

                ManagerTeamJpaValueObject newTeamMember = new ManagerTeamJpaValueObject(teamMember.getId(), staffJpa.get(), managerJpa1.getId());

                managerJpa1.addTeamMember(newTeamMember);

                managerRepository.save(managerJpa1);

            } else {
                throw new IllegalArgumentException("Staff id is not recognised");
            }
        } else {
            throw new IllegalArgumentException("Manager id is not recognised");
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

            jmsTemplate.convertAndSend("SKILL.CREATE.QUEUE", eventToJson);

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

            jmsTemplate.convertAndSend("SKILL.EDIT.QUEUE", eventToJson);

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

                jmsTemplate.convertAndSend("EMPLOYEE.DELETE.SKILL.QUEUE", eventToJson);

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

            jmsTemplate.convertAndSend("CATEGORY.CREATE.QUEUE", eventToJson);

            LOG.info("Message sent to CATEGORY.CREATE.QUEUE");
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }

    @Override
    public void editCategory(IEditCategoryCommand editCategoryCommand) {

        EmployeeEditCategoryEvent event = new EmployeeEditCategoryEvent();
        event.setId(editCategoryCommand.getId());
        event.setDescription(editCategoryCommand.getDescription());

        try {
            String eventToJson = objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend("CATEGORY.EDIT.QUEUE", eventToJson);

        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }

    @Override
    public void deleteCategory(IDeleteCategoryCommand editCategoryCommand) {

        EmployeeDeleteCategoryEvent event = new EmployeeDeleteCategoryEvent();
        event.setId(editCategoryCommand.getId());

        try {
            String eventToJon = objectMapper.writeValueAsString(event);

            jmsTemplate.convertAndSend("SKILL.CATEGORY.DELETE.QUEUE", eventToJon);
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }

    }


}
