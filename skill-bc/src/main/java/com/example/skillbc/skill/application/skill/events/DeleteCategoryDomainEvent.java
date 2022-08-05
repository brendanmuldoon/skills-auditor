package com.example.skillbc.skill.application.skill.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class DeleteCategoryDomainEvent extends ApplicationEvent {

    private String id;
    public DeleteCategoryDomainEvent(Object source, String id) {
        super(source);
        this.id = id;
    }
}
