package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.application.manager.dto.ManagerTeamDTO;
import com.example.employeebc.employee.domain.common.FullName;
import com.example.employeebc.employee.domain.manager.ManagerTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class ManagerControllerTests {

    private IManagerQueryHandler queryHandler;

    private IManagerApplicationService applicationService;

    private IIdentityService identityService;

    private MockMvc mockMvc;

    Resource userDetails;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        identityService = Mockito.mock(IIdentityService.class);
        applicationService = Mockito.mock(IManagerApplicationService.class);
        queryHandler = Mockito.mock(IManagerQueryHandler.class);
        ManagerController managerController = new ManagerController(queryHandler, applicationService, identityService);
        mockMvc = MockMvcBuilders.standaloneSetup(managerController)
                .build();

        userDetails = new ClassPathResource("data/userDetails.json");

  }

    @ParameterizedTest
    @ValueSource(strings = {"/manager/team/findTeamByManagerId/{manager_id}",
            "/manager/team/bySkill/{skill_id}"})
    void test01(String uri) throws Exception {
      Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(false);

      mockMvc.perform(MockMvcRequestBuilders.post(uri, "123")
                      .header("Content-Type", "application/json;charset=utf-8")
                      .content((convertFileToString(userDetails))))
              .andExpect(MockMvcResultMatchers.status().isUnauthorized());

  }
    @Test
    void test02() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(queryHandler.findTeamByManagerId(Mockito.anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/team/findTeamByManagerId/{manager_id}", "123")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(userDetails))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void test03() throws Exception {

        List<ManagerTeam> response = new ArrayList<>();
        response.add(new ManagerTeam("1", "1", new FullName("TEST", "test")));

        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(queryHandler.findTeamByManagerId(Mockito.anyString())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/team/findTeamByManagerId/{manager_id}", "123")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(userDetails))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    void test04() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(queryHandler.findTeamBySkillId(Mockito.anyString(), Mockito.anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/team/bySkill/{skill_id}", "123")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(userDetails))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void test05() throws Exception {
        List<ManagerTeamDTO> response = new ArrayList<>();
        response.add(new ManagerTeamDTO("test", "test", "test", new ArrayList<>()));

        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(queryHandler.findTeamBySkillId(Mockito.anyString(), Mockito.anyString())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/team/bySkill/{skill_id}", "123")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(userDetails))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void test06() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/team/expiredSkills")
        .header("Content-Type", "application/json;charset=utf-8")
        .content((convertFileToString(userDetails))))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    void test07() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/team/addToTeam/{staff_id}", "123")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(userDetails))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void test08() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/createSkill")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(userDetails))))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void test09() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/manager/createSkill")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(userDetails))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }





    private String convertFileToString(Resource resource) throws IOException {
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }

}