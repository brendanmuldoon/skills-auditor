package com.example.employeebc.employee.infrastructure.manager;

import com.example.employeebc.employee.infrastructure.staff.StaffJpa;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="manager_team")
@Table(name="manager_team")
@Setter
@Getter
@ToString
public class ManagerTeamJpaValueObject {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "team_sequence",
            sequenceName = "team_sequence_id",
            allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="team_sequence")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private StaffJpa staff;

    @Column(name = "manager_id")
    private String manager;

    @Column(name = "fullname_firstname")
    private String fullname_firstname;

    @Column(name="fullname_surname")
    private String fullname_surname;

    public ManagerTeamJpaValueObject(){}

    public ManagerTeamJpaValueObject(
            long id,
            StaffJpa staffJpa,
            String manager,
            String fullname_firstname,
            String fullname_surname) {
        this.id = id;
        this.staff = staffJpa;
        this.manager = manager;
        this.fullname_firstname = fullname_firstname;
        this.fullname_surname = fullname_surname;
    }

}
