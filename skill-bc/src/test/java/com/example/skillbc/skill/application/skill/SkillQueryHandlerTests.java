package com.example.skillbc.skill.application.skill;

import com.example.skillbc.skill.application.skill.interfaces.ISkillRepository;
import com.example.skillbc.skill.domain.skill.DTO.SkillDTOList;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SkillQueryHandlerTests {

    @Mock
    private ISkillRepository skillRepository;

    @InjectMocks
    private SkillQueryHandler classUnderTest;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test01() {
        List<SkillJpa> list = new ArrayList<>();
        list.add(new SkillJpa("1", "test", new CategoryJpaValueObject("1", "test")));

        Mockito.when(skillRepository.findAll()).thenReturn(list);

        List<SkillJpa> response = (List<SkillJpa>) classUnderTest.findAll();

        assertEquals(1, response.size());
    }

    @Test
    void test02() {

        Mockito.when(skillRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        Optional<?> response = classUnderTest.findBySkillId("1");

        assertTrue(response.isEmpty());
    }

    @Test
    void test03() {

        Mockito.when(skillRepository.findById(Mockito.anyString())).thenReturn(Optional.of(SkillJpa.skillJpaOf("1", "test", CategoryJpaValueObject.categoryJpaOf("1", "test"))));

        Optional<?> response = classUnderTest.findBySkillId("1");

        assertTrue(response.isPresent());
    }

    @Test
    void test04() {

        Mockito.when(skillRepository.findByCategoryId(Mockito.anyString())).thenReturn(new ArrayList<>());

        SkillDTOList response = classUnderTest.findByCategoryId("1");

        assertEquals(0, response.getSkills().size());

    }

    @Test
    void test05() {

        List<SkillJpa> list = new ArrayList<>();
        list.add(SkillJpa.skillJpaOf("1", "test", CategoryJpaValueObject.categoryJpaOf("1", "test")));

        Mockito.when(skillRepository.findByCategoryId(Mockito.anyString())).thenReturn(list);

        SkillDTOList response = classUnderTest.findByCategoryId("1");

        assertEquals(1, response.getSkills().size());

    }

}