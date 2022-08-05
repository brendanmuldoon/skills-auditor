package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.domain.common.Entity;
import com.example.skillbc.skill.domain.common.Identity;
import com.example.skillbc.skill.application.skill.events.NewSkillAddedDomainEvent;
import lombok.ToString;

@ToString
public class Skill extends Entity { // Aggregate

    private String description;
    private String category;

    public Skill(Identity id, String description, String category) {
        super(id);
        setDescription(description);
        this.category = category;
        this.addDomainEvent(new NewSkillAddedDomainEvent(this, id.id(), description, category));
    }

    public static Skill skillOf(Identity newId, String description, String category) {
        return new Skill(newId, description, category);
    }


    private void setDescription(String description) {
        assertArgumentNotEmpty(description, "Skill description must not be empty");
        this.description=description;
    }

    public Identity id() {
        return this.id;
    }

    public String description() {
        return this.description;
    }

    public String category() {
        return this.category;
    }

    public void update(String description) {
        this.description = description;
    }
}
