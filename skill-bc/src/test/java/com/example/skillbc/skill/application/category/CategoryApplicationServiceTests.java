package com.example.skillbc.skill.application.category;

import com.example.skillbc.skill.application.category.events.SkillCreateCategoryEvent;
import com.example.skillbc.skill.application.category.events.SkillEditCategoryEvent;
import com.example.skillbc.skill.application.category.interfaces.ICategoryRepository;
import com.example.skillbc.skill.application.category.interfaces.ICategoryToCategoryJpaMapper;
import com.example.skillbc.skill.application.skill.events.SkillCategoryDeleteEvent;
import com.example.skillbc.skill.infrastructure.skill.CategoryJpaValueObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Optional;

public class CategoryApplicationServiceTests {

    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private ICategoryToCategoryJpaMapper categoryToCategoryJpaMapper;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private CategoryApplicationService classUnderTest;

    TextMessage message;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        message = Mockito.mock(TextMessage.class);
    }

    @Test
    void test01() throws JsonProcessingException, JMSException {
        SkillCreateCategoryEvent event = new SkillCreateCategoryEvent();
        event.setId("1");
        event.setDescription("test");

        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue(message.getText(), SkillCreateCategoryEvent.class)).thenReturn(event);
        Mockito.when(categoryRepository.findByDescription(Mockito.anyString())).thenReturn(Optional.of(new CategoryJpaValueObject("1", "test")));
        classUnderTest.createNewCategoryListener(message);

        Mockito.verify(categoryRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    void test02() throws JsonProcessingException, JMSException {
        SkillCreateCategoryEvent event = new SkillCreateCategoryEvent();
        event.setId("1");
        event.setDescription("test");

        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue(message.getText(), SkillCreateCategoryEvent.class)).thenReturn(event);
        Mockito.when(categoryRepository.findByDescription(Mockito.anyString())).thenReturn(Optional.empty());
        classUnderTest.createNewCategoryListener(message);

        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void test03() throws JsonProcessingException, JMSException {

        SkillEditCategoryEvent event = new SkillEditCategoryEvent();
        event.setDescription("test");
        event.setId("1");
        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue(message.getText(), SkillEditCategoryEvent.class)).thenReturn(event);
        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        classUnderTest.editCategoryListener(message);
        Mockito.verify(categoryRepository, Mockito.times(0)).save(Mockito.any());

    }

    @Test
    void test04() throws JsonProcessingException, JMSException {

        SkillEditCategoryEvent event = new SkillEditCategoryEvent();
        event.setDescription("test");
        event.setId("1");
        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue(message.getText(), SkillEditCategoryEvent.class)).thenReturn(event);
        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new CategoryJpaValueObject("1", "test")));
        Mockito.when(categoryToCategoryJpaMapper.map(Mockito.any())).thenReturn(new CategoryJpaValueObject("1", "test"));
        classUnderTest.editCategoryListener(message);

        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void test05() throws JsonProcessingException, JMSException {

        SkillCategoryDeleteEvent event = new SkillCategoryDeleteEvent();
        event.setId("1");
        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue(message.getText(), SkillCategoryDeleteEvent.class)).thenReturn(event);
        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        classUnderTest.handleDeleteCategory(message);
        Mockito.verify(categoryRepository, Mockito.times(0)).delete(Mockito.any());

    }

    @Test
    void test06() throws JsonProcessingException, JMSException {

        SkillCategoryDeleteEvent event = new SkillCategoryDeleteEvent();
        event.setId("1");
        Mockito.when(message.getText()).thenReturn("test");
        Mockito.when(objectMapper.readValue(message.getText(), SkillCategoryDeleteEvent.class)).thenReturn(event);
        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new CategoryJpaValueObject("1", "test")));
        Mockito.when(categoryToCategoryJpaMapper.map(Mockito.any())).thenReturn(new CategoryJpaValueObject("1", "test"));
        classUnderTest.handleDeleteCategory(message);

        Mockito.verify(categoryRepository, Mockito.times(1)).delete(Mockito.any());

    }
}