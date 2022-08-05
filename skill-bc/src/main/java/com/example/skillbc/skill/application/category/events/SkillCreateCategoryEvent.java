package com.example.skillbc.skill.application.category.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SkillCreateCategoryEvent implements Serializable {

    private String id;
    private String description;
}
