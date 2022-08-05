package com.example.employeebc.employee.infrastructure.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="staff_skills")
@Table(name="staff_skills")
@Setter
@Getter
@ToString
public class StaffSkillJpaValueObject {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "skills_sequence",
            sequenceName = "skills_sequence_id",
            allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="skills_sequence")
    private long id;

    @Column(name = "skill_id")
    private String skillId;

    @Column(name = "strength_of_skill")
    private String strengthOfSkill;

    @Column(name = "expiry")
    private LocalDate expiry;

    @Column(name = "staff_id")
    private String staff;

    public StaffSkillJpaValueObject(){}

    public StaffSkillJpaValueObject(
            long id,
            String skillId,
            String strengthOfSkill,
            LocalDate expiry) {
        this.id = id;
        this.skillId = skillId;
        this.strengthOfSkill = strengthOfSkill;
        this.expiry = expiry;
    }

    public static StaffSkillJpaValueObject staffSkillJpaOf(Long id, String skillId, String strengthOfSkill, LocalDate expirationDate) {

        return new StaffSkillJpaValueObject(id, skillId, strengthOfSkill, expirationDate);
    }
}
