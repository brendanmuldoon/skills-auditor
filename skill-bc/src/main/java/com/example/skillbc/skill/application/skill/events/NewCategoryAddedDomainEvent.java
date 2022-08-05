package com.example.skillbc.skill.application.skill.events;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class NewCategoryAddedDomainEvent extends ApplicationEvent {

    private String id;
    private String description;
    public NewCategoryAddedDomainEvent(Object source, String id, String description) {
        super(source);
        this.id=id;
        this.description=description;
    }
}
