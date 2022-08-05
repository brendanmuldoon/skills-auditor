package com.example.skillbc.skill.application.skill.events;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class NewSkillAddedDomainEvent extends ApplicationEvent {

    private String aggregateID;
    private String description;
    private String categoryId;


    public NewSkillAddedDomainEvent(Object source, String aggregateID, String description, String categoryId) {
        super(source);
        this.aggregateID=aggregateID;
        this.description=description;
        this.categoryId=categoryId;
    }
}
