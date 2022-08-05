package com.example.skillbc.skill.infrastructure.skill;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "category")
@Table(name = "category")
@Setter
@Getter
@ToString
public class CategoryJpaValueObject {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    protected CategoryJpaValueObject() {
    }

    public CategoryJpaValueObject(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CategoryJpaValueObject categoryJpaOf(String id, String description) {
        return new CategoryJpaValueObject(id, description);
    }
}
