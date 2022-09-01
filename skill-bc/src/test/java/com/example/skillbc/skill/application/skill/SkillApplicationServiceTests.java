package com.example.skillbc.skill.application.skill;

import com.example.skillbc.skill.application.category.interfaces.ICategoryRepository;
import com.example.skillbc.skill.application.skill.events.SkillCategoryDeleteEvent;
import com.example.skillbc.skill.application.skill.events.SkillCreateSkillEvent;
import com.example.skillbc.skill.application.skill.events.SkillDeleteSkillEvent;
import com.example.skillbc.skill.application.skill.events.SkillEditSkillEvent;
import com.example.skillbc.skill.application.skill.interfaces.ISkillJpaToSkillMapper;
import com.example.skillbc.skill.application.skill.interfaces.ISkillRepository;
import com.example.skillbc.skill.application.skill.interfaces.ISkillToSkillJpaMapper;
import com.example.skillbc.skill.domain.common.Identity;
import com.example.skillbc.skill.domain.skill.Skill;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillApplicationServiceTests {

    @Mock
    private ISkillRepository skillRepository;
    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private ISkillToSkillJpaMapper skillToSkillJpaMapper;
    @Mock
    private ISkillJpaToSkillMapper skillJpaToSkillMapper;
    @Mock
    private JmsTemplate jmsTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private TextMessage message;
    @InjectMocks
    private SkillApplicationService classUnderTest;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test01() throws JMSException, JsonProcessingException {


        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillCreateSkillEvent.class)).thenReturn(new SkillCreateSkillEvent());
        Mockito.when(skillRepository.findByDescription(Mockito.anyString())).thenReturn(Optional.of(new SkillJpa(
                "1",
                "test",
                new CategoryJpaValueObject("1", "test")
        )));

        classUnderTest.createNewSkillListener(message);

        Mockito.verify(skillRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    void test02() throws JMSException, JsonProcessingException {


        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillCreateSkillEvent.class)).thenReturn(new SkillCreateSkillEvent());
        Mockito.when(skillRepository.findByDescription(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        classUnderTest.createNewSkillListener(message);

        Mockito.verify(skillRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    void test03() throws JMSException, JsonProcessingException {

        SkillCreateSkillEvent event = new SkillCreateSkillEvent();
        event.setDescription("test");
        event.setId("1");
        event.setCategory("1");

        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillCreateSkillEvent.class)).thenReturn(event);
        Mockito.when(skillRepository.findByDescription(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(
                new CategoryJpaValueObject("1", "test")
        ));
        Mockito.when(skillToSkillJpaMapper.map(Mockito.any(), Mockito.any())).thenReturn(new SkillJpa("1", "Test",
                new CategoryJpaValueObject("1", "test")));

        classUnderTest.createNewSkillListener(message);

        Mockito.verify(skillRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void test04() throws JMSException, JsonProcessingException {
        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillEditSkillEvent.class)).thenReturn(new SkillEditSkillEvent());
        Mockito.when(skillRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        classUnderTest.editSkillListener(message);

        Mockito.verify(skillRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    void test05() throws JMSException, JsonProcessingException {

        Optional<SkillJpa> skillJpa = Optional.of(new SkillJpa("1", "Test",
                new CategoryJpaValueObject("1", "test")));
        SkillEditSkillEvent event = new SkillEditSkillEvent();
        event.setDescription("test");
        event.setId("1");


        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillEditSkillEvent.class)).thenReturn(event);
        Mockito.when(skillRepository.findById(Mockito.anyString())).thenReturn(skillJpa);

        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new CategoryJpaValueObject("1", "test")));
        Mockito.when(skillJpaToSkillMapper.map(Mockito.any())).thenReturn(new Skill(new Identity("1"), "test", "test"));
        Mockito.when(skillToSkillJpaMapper.map(Mockito.any(), Mockito.any())).thenReturn(skillJpa.get());

        classUnderTest.editSkillListener(message);

        Mockito.verify(skillRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void test06() throws JMSException, JsonProcessingException {
        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillDeleteSkillEvent.class)).thenReturn(new SkillDeleteSkillEvent());
        Mockito.when(skillRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        classUnderTest.deleteSkillListener(message);

        Mockito.verify(skillRepository, Mockito.times(0)).delete(Mockito.any());
    }

    @Test
    void test07() throws JMSException, JsonProcessingException {
        SkillDeleteSkillEvent event = new SkillDeleteSkillEvent();
        event.setId("123");

        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillDeleteSkillEvent.class)).thenReturn(event);
        Mockito.when(skillRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new SkillJpa("1", "test", new CategoryJpaValueObject("1", "test"))));

        classUnderTest.deleteSkillListener(message);

        Mockito.verify(skillRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void test08() throws JMSException, JsonProcessingException {
        String eventToJson = "test";
        SkillCategoryDeleteEvent event = new SkillCategoryDeleteEvent();
        event.setId("1");

        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillCategoryDeleteEvent.class)).thenReturn(event);
        Mockito.when(objectMapper.writeValueAsString(event)).thenReturn(eventToJson);
        Mockito.when(skillRepository.findByCategoryId(Mockito.anyString())).thenReturn(new ArrayList<>());


        classUnderTest.handleDeleteCategoryEvent(message);

        Mockito.verify(jmsTemplate, Mockito.times(1)).convertAndSend("CATEGORY.DELETE.QUEUE", eventToJson);
    }

    @Test
    void test09() throws JMSException, JsonProcessingException {

        String eventToJson = "test";
        SkillCategoryDeleteEvent event = new SkillCategoryDeleteEvent();
        event.setId("1");

        List<SkillJpa> list = new ArrayList<>();
        list.add(new SkillJpa("1", "test", new CategoryJpaValueObject("1", "test")));
        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue("test", SkillCategoryDeleteEvent.class)).thenReturn(event);
        Mockito.when(skillRepository.findByCategoryId(Mockito.anyString())).thenReturn(list);

        classUnderTest.handleDeleteCategoryEvent(message);

        Mockito.verify(jmsTemplate, Mockito.times(0)).convertAndSend("CATEGORY.DELETE.QUEUE", eventToJson);
    }


}