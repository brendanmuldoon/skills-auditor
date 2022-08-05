package com.example.employeebc.employee.infrastructure.manager;

import com.example.employeebc.employee.domain.manager.interfaces.IManagerJpa;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="manager")
@Table(name = "employee")
@ToString
public class ManagerJpa implements IManagerJpa {

    @Id
    @Column
    private String id;

    @Column(name = "fullname_firstname")
    private String fullname_firstname;

    @Column(name="fullname_surname")
    private String fullname_surname;

    @Column(name = "address_housenumber")
    private String address_housenumber;

    @Column(name="address_streetname")
    private String address_streetname;

    @Column(name = "address_postcode")
    private String address_postcode;

    @Column(name="role")
    private String role;

    @Column(name = "securitycredentials_username")
    private String securitycredentials_username;

    @Column(name="securitycedentials_password")
    private String securitycedentials_password;

    @OneToMany(mappedBy = "manager", cascade = {CascadeType.ALL})
    private List<ManagerTeamJpaValueObject> team;


    protected ManagerJpa(){}

    public ManagerJpa(String id,
                    String fullname_firstname,
                    String fullname_surname,
                    String address_housenumber,
                    String address_streetname,
                    String address_postcode,
                    String role,
                    String securitycredentials_username,
                    String securitycedentials_password) {
        this.id = id;
        this.fullname_firstname = fullname_firstname;
        this.fullname_surname = fullname_surname;
        this.address_housenumber = address_housenumber;
        this.address_streetname = address_streetname;
        this.address_postcode = address_postcode;
        this.role = role;
        this.securitycredentials_username = securitycredentials_username;
        this.securitycedentials_password = securitycedentials_password;
        team = new ArrayList<>();
    }

    public static ManagerJpa managerJpaOf(String id,
                                      String fullname_firstname,
                                      String fullname_surname,
                                      String address_housenumber,
                                      String address_streetname,
                                      String address_postcode,
                                      String role,
                                      String securitycredentials_username,
                                      String securitycedentials_password) {
        return new ManagerJpa(id,
                fullname_firstname,
                fullname_surname,
                address_housenumber,
                address_streetname,
                address_postcode,
                role,
                securitycredentials_username,
                securitycedentials_password);

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname_firstname() {
        return fullname_firstname;
    }

    public void setFullname_firstname(String fullname_firstname) {
        this.fullname_firstname = fullname_firstname;
    }

    public String getFullname_surname() {
        return fullname_surname;
    }

    public void setFullname_surname(String fullname_surname) {
        this.fullname_surname = fullname_surname;
    }

    public String getAddress_housenumber() {
        return address_housenumber;
    }

    public void setAddress_housenumber(String address_housenumber) {
        this.address_housenumber = address_housenumber;
    }

    public String getAddress_streetname() {
        return address_streetname;
    }

    public void setAddress_streetname(String address_streetname) {
        this.address_streetname = address_streetname;
    }

    public String getAddress_postcode() {
        return address_postcode;
    }

    public void setAddress_postcode(String address_postcode) {
        this.address_postcode = address_postcode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSecuritycredentials_username() {
        return securitycredentials_username;
    }

    public void setSecuritycredentials_username(String securitycredentials_username) {
        this.securitycredentials_username = securitycredentials_username;
    }

    public String getSecuritycedentials_password() {
        return securitycedentials_password;
    }

    public void setSecuritycedentials_password(String securitycedentials_password) {
        this.securitycedentials_password = securitycedentials_password;
    }

    public List<ManagerTeamJpaValueObject> getTeam() {
        return team;
    }

    public void setTeam(List<ManagerTeamJpaValueObject> team) {
        this.team = team;
    }

    @Override
    public void removeTeamMember(ManagerTeamJpaValueObject newTeamMember) {
        this.team.remove(newTeamMember);
    }

    public void addTeamMember(ManagerTeamJpaValueObject managerTeamJpaValueObject) {
        team.add(managerTeamJpaValueObject);
    }

}
