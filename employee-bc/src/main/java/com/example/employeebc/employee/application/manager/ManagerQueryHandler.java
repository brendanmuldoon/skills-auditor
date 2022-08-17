package com.example.employeebc.employee.application.manager;

import com.example.employeebc.employee.application.manager.interfaces.IManagerRepository;
import com.example.employeebc.employee.application.manager.mappers.ManagerJpaToDTOMapper;
import com.example.employeebc.employee.application.manager.queries.EmployeeSkillDTOList;
import com.example.employeebc.employee.domain.manager.DTO.ManagerDTO;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTO;
import com.example.employeebc.employee.domain.manager.interfaces.IGetTeamBySkillIdQuery;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import com.example.employeebc.employee.ui.manager.IManagerQueryHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ManagerQueryHandler implements IManagerQueryHandler {

    private IManagerRepository managerRepository;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private ApplicationEventPublisher eventPublisher;

    private void manageDomainEvents(List<ApplicationEvent> events) {
        for (ApplicationEvent event : events){
            LOG.info("event " + event);
            eventPublisher.publishEvent(event);
            //eventStoreService.append(events);
        }
    }

    @Override
    public Iterable<ManagerJpa> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public Optional<ManagerDTO> findByManagerId(String managerId) {
        Optional<ManagerJpa> response = managerRepository.findById(managerId);
        if(response.isPresent()) {
            return ManagerJpaToDTOMapper.convertManagerDetailsToDTO(response.get());
        }
        return Optional.empty();
    }

    @Override
    public List<ManagerTeamDTO> findTeamByManagerId(String managerId) {
        Optional<ManagerJpa> response = managerRepository.findById(managerId);
        if(response.isPresent()) {
            return ManagerJpaToDTOMapper.convertManagerTeamToDTO(response.get());
        }
        return new ArrayList<>();
    }

    @Override
    public List<ManagerTeamDTO> findTeamBySkillId(String managerId, String skillId) {
        Optional<ManagerJpa> response = managerRepository.findById(managerId);
        if(response.isPresent()) {
            List<ManagerTeamDTO> membersWithSkill = new ArrayList<>();
            List<ManagerTeamDTO> team = ManagerJpaToDTOMapper.convertManagerTeamToDTO(response.get());
            team.forEach(t -> t.getSkills().forEach(s -> {
                if(s.getSkillId().equals(skillId)) {
                    membersWithSkill.add(t);
                }
            } ));

            return membersWithSkill;
        }
        return new ArrayList<>();
    }

    @Override
    public EmployeeSkillDTOList findSkillsByCategory(String categoryId) {


        String URL = String.format("http://localhost:8081/skill/findAllSkillsByCategory/%s", categoryId); //Assuming the other context is running on 8901
        RestTemplate restTemplate = new RestTemplate();
        EmployeeSkillDTOList myRequiredData = new EmployeeSkillDTOList();

        try {

            myRequiredData = restTemplate.getForObject(URL, EmployeeSkillDTOList.class);

            if(myRequiredData != null && !myRequiredData.getSkills().isEmpty()) {

                return myRequiredData;

            }


        } catch (Exception e) {

            LOG.error(e.getMessage());
        }


        return myRequiredData;
    }


    // a method that is subscribed to the skill context and adds data to a var somewhere in here
}
