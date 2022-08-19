package com.example.employeebc.employee.application.manager;

import com.example.employeebc.employee.application.manager.interfaces.IManagerJpaToManagerMapper;
import com.example.employeebc.employee.application.manager.interfaces.IManagerRepository;
import com.example.employeebc.employee.application.manager.mappers.ManagerJpaToDTOMapper;
import com.example.employeebc.employee.application.manager.queries.EmployeeSkillDTOList;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTO;
import com.example.employeebc.employee.domain.manager.Manager;
import com.example.employeebc.employee.domain.manager.ManagerTeam;
import com.example.employeebc.employee.domain.staff.DTO.StaffDTO;
import com.example.employeebc.employee.infrastructure.manager.ManagerJpa;
import com.example.employeebc.employee.ui.manager.IManagerQueryHandler;
import com.example.employeebc.employee.ui.staff.IStaffQueryHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ManagerQueryHandler implements IManagerQueryHandler {

    private IManagerRepository managerRepository;

    private IStaffQueryHandler staffQueryHandler;

    private IManagerJpaToManagerMapper managerJpaToManagerMapper;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public List<ManagerTeam> findTeamByManagerId(String managerId) {
        Optional<ManagerJpa> response = managerRepository.findById(managerId);
        if(response.isPresent()) {
            Manager manager = managerJpaToManagerMapper.map(response.get());
            return manager.retrieveTeam();
        }
        //return response.map(ManagerJpaToDTOMapper::convertManagerTeamToDTO).orElseGet(ArrayList::new);
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


        String URL = String.format("http://localhost:8081/skill/findAllSkillsByCategory/%s", categoryId);
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

    @Override
    public List<?> findAllWithExpiredSkills() {
        List<StaffDTO> response = staffQueryHandler.findAllWithExpiredSkills();
        if(!response.isEmpty()) {
            return response;
        }
        return new ArrayList<>();
    }

}
