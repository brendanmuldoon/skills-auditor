package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.domain.common.Entity;
import com.example.skillbc.skill.domain.common.Identity;
import com.example.skillbc.skill.application.skill.events.DeleteCategoryDomainEvent;
import com.example.skillbc.skill.application.skill.events.NewCategoryAddedDomainEvent;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class Category extends Entity {

    private String description;

    protected Category(Identity id, String description) {
        super(id);
        setDescription(description);
        this.addDomainEvent(new NewCategoryAddedDomainEvent(this, id.id(), description));
    }

    public Category(Category category) {
        this(category.id(), category.description());
    }

    public static Category categoryOf(Identity id, String description) {
        return new Category(id, description);
    }


    public Category(Identity id) {
        super(id);
        this.addDomainEvent(new DeleteCategoryDomainEvent(this, id.id()));
    }

    public static Category delete(Identity identity) {
        return new Category(identity);
    }


    private void setDescription(String description) {
        assertArgumentNotEmpty(description, "Category description must not be null");
        this.description=description;
    }

    public Identity id() {
        return this.id;
    }

    public String description(){
        return this.description;
    }
}
