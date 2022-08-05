package com.example.skillbc.skill.application.category.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SkillEditCategoryEvent {

    private String id;
    private String description;
}
