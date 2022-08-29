package com.example.employeebc.employee.ui.staff;

import com.example.employeebc.employee.application.staff.commands.UpdateStaffDetailsCommand;
import com.example.employeebc.employee.domain.common.Address;
import com.example.employeebc.employee.domain.common.SecurityCredentials;
import com.example.employeebc.employee.domain.staff.Staff;
import com.example.employeebc.employee.ui.manager.IIdentityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.file.Files;

@AutoConfigureMockMvc
@SpringBootTest
public class StaffControllerTests {


    private IStaffApplicationService applicationService;

    private IIdentityService identityService;
    private MockMvc mockMvc;

    Resource updateStaffDetailsCommand;
    Resource addStaffSkillCommand;
    Resource deleteStaffSkillCommand;
    Resource updateStaffSkillCommand;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        identityService = Mockito.mock(IIdentityService.class);
        applicationService = Mockito.mock(IStaffApplicationService.class);
        StaffController staffController = new StaffController(applicationService, identityService);
        mockMvc = MockMvcBuilders.standaloneSetup(staffController)
                .build();

        updateStaffDetailsCommand = new ClassPathResource("data/updateStaffDetailsCommand.json");
        addStaffSkillCommand = new ClassPathResource("data/addStaffSkillCommand.json");
        deleteStaffSkillCommand = new ClassPathResource("data/removeStaffSkillCommand.json");
        updateStaffSkillCommand = new ClassPathResource("data/updateStaffSkillCommand.json");
    }

    @Test
    void test01() throws Exception {

        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(applicationService).updateStaffDetails(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.put("/staff/updateDetails")
                .header("Content-Type", "application/json;charset=utf-8")
                .content((convertFileToString(updateStaffDetailsCommand))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void test02() throws Exception {

        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(false);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.put("/staff/updateDetails")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(updateStaffDetailsCommand))))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    void test03() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/staff/staffSkill/add")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(addStaffSkillCommand))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test04() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(false);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/staff/staffSkill/add")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(addStaffSkillCommand))))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void test05() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/staff/staffSkill/removeSkill")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(deleteStaffSkillCommand))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test06() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(false);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/staff/staffSkill/removeSkill")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(deleteStaffSkillCommand))))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    void test07() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(true);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/staff/staffSkill/updateSkill")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(updateStaffSkillCommand))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void test08() throws Exception {
        Mockito.when(identityService.isAdmin(Mockito.any())).thenReturn(false);
        Mockito.when(identityService.isSpecifiedUser(Mockito.any(), Mockito.any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.put("/staff/staffSkill/updateSkill")
                        .header("Content-Type", "application/json;charset=utf-8")
                        .content((convertFileToString(updateStaffSkillCommand))))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    private String convertFileToString(Resource resource) throws IOException {
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }


}