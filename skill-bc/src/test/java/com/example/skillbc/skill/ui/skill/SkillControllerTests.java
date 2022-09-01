package com.example.skillbc.skill.ui.skill;

import com.example.skillbc.skill.domain.skill.DTO.CategoryDTO;
import com.example.skillbc.skill.domain.skill.DTO.SkillDTO;
import com.example.skillbc.skill.domain.skill.DTO.SkillDTOList;
import com.example.skillbc.skill.domain.skill.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
public class SkillControllerTests {

    private ISkillQueryHandler queryHandler;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        queryHandler = Mockito.mock(ISkillQueryHandler.class);
        SkillController skillController = new SkillController(queryHandler);
        mockMvc = MockMvcBuilders.standaloneSetup(skillController).build();
    }

    @Test
    void test01() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/{skill_id}", "1")
                .header("Content-Type", "application/json;charset=utf-8")
                ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void test02() throws Exception {
        Mockito.when(queryHandler.findBySkillId(Mockito.anyString())).thenReturn(Optional.of(new SkillDTO()));
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/{skill_id}", "1")
                .header("Content-Type", "application/json;charset=utf-8")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void test03() throws Exception {
        SkillDTOList skillDTOList = new SkillDTOList();
        Mockito.when(queryHandler.findByCategoryId(Mockito.anyString())).thenReturn(skillDTOList);
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/findAllSkillsByCategory/{category_id}", "1")
                .header("Content-Type", "application/json;charset=utf-8")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void test04() throws Exception {
        SkillDTOList skillDTOList = new SkillDTOList();
        List<SkillDTO> skills =  new ArrayList<>();
        skills.add(new SkillDTO("1", "test", new CategoryDTO("1","test")));
        skillDTOList.setSkills(skills);
        Mockito.when(queryHandler.findByCategoryId(Mockito.anyString())).thenReturn(skillDTOList);
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/findAllSkillsByCategory/{category_id}", "1")
                .header("Content-Type", "application/json;charset=utf-8")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}