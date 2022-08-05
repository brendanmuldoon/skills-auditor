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
import com.example.skillbc.skill.ui.skill.ISkillApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Optional;

@AllArgsConstructor
@Service
@EnableJms
public class SkillApplicationService implements ISkillApplicationService {

    private ISkillRepository skillRepository;
    private ICategoryRepository categoryRepository;
    private ISkillToSkillJpaMapper skillToSkillJpaMapper;
    private ISkillJpaToSkillMapper skillJpaToSkillMapper;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private JmsTemplate jmsTemplate;

    private ObjectMapper objectMapper;

    @JmsListener(destination = "SKILL.CREATE.QUEUE")
    public void createNewSkillListener(Message message) {

        LOG.info("Received message from SKILL.CREATE.QUEUE");

        try {

            if (message instanceof TextMessage) {

                String messageBody = ((TextMessage) message).getText();

                SkillCreateSkillEvent event = objectMapper.readValue(messageBody, SkillCreateSkillEvent.class);

                Optional<SkillJpa> skillJpa = skillRepository.findByDescription(event.getDescription());

                if (skillJpa.isPresent()) {

                    LOG.info("Skill already exists");

                }

                else {

                    Optional<CategoryJpaValueObject> category = categoryRepository.findById(event.getCategory());

                    if ( category.isPresent()) {
                        Identity identity = new Identity(event.getId());

                        Skill skill = Skill.skillOf(identity, event.getDescription(), event.getId());
                        skillRepository.save(skillToSkillJpaMapper.map(skill, category.get()));
                        LOG.info("New Skill successfully added");

                    } else {

                        LOG.error("Invalid category");

                    }

                }

            }

        } catch (Exception e) {

            LOG.error(e.getMessage());

        }

    }

    @JmsListener(destination = "SKILL.EDIT.QUEUE")
    public void editSkillListener(Message message) {

        LOG.info("Received message from SKILL.EDIT.QUEUE");

        try {

            if (message instanceof TextMessage) {

                String messageBody = ((TextMessage) message).getText();

                SkillEditSkillEvent event = objectMapper.readValue(messageBody, SkillEditSkillEvent.class);

                Optional<SkillJpa> skillJpa = skillRepository.findById(event.getId());

                if (skillJpa.isEmpty()) {

                    LOG.info("Skill doest not exist");

                }

                else {

                    Skill skill = skillJpaToSkillMapper.map(skillJpa.get());

                    skill.update(event.getDescription());

                    Optional<CategoryJpaValueObject> category = categoryRepository.findById(skill.category());

                    skillRepository.save(skillToSkillJpaMapper.map(skill, category.get()));

                    LOG.info("Skill successfully updated");

                }

            }

        } catch (Exception e) {

            LOG.error(e.getMessage());

        }

    }

    @JmsListener(destination = "SKILL.DELETE.QUEUE")
    public void deleteSkillListener(Message message) {

        LOG.info("Received message from SKILL.DELETE.QUEUE");

        try {

            if (message instanceof TextMessage) {

                String messageBody = ((TextMessage) message).getText();

                SkillDeleteSkillEvent event = objectMapper.readValue(messageBody, SkillDeleteSkillEvent.class);

                Optional<SkillJpa> skillJpa = skillRepository.findById(event.getId());

                if (skillJpa.isEmpty()) {

                    LOG.info("Skill doest not exist");

                }

                else {

                    skillRepository.delete(skillJpa.get());

                    LOG.info("Skill deleted");

                }

            }

        } catch (Exception e) {

            LOG.error(e.getMessage());

        }

    }

    @JmsListener(destination = "SKILL.CATEGORY.DELETE.QUEUE")
    public void handleDeleteCategoryEvent(Message message) {

        LOG.info("Received message from SKILL.CATEGORY.DELETE.QUEUE");

        try {

            if (message instanceof TextMessage) {

                String messageBody = ((TextMessage) message).getText();

                SkillCategoryDeleteEvent event = objectMapper.readValue(messageBody, SkillCategoryDeleteEvent.class);

                if(!skillRepository.findByCategoryId(event.getId()).isEmpty()) {

                    LOG.info("Cannot delete, Category is in use");

                } else {

                    String eventToJon = objectMapper.writeValueAsString(event);

                    jmsTemplate.convertAndSend("CATEGORY.DELETE.QUEUE", eventToJon);

                }

            }

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }

    }

}
