package com.example.employeebc.employee.domain.manager.interfaces;

import com.example.employeebc.employee.infrastructure.manager.ManagerTeamJpaValueObject;

import java.util.List;

public interface IManagerJpa {

    String getId();

    void setId(String id);

    String getFullname_firstname();

    void setFullname_firstname(String fullname_firstname);

    String getFullname_surname();

    void setFullname_surname(String fullname_surname);

    String getAddress_housenumber();

    void setAddress_housenumber(String address_housenumber);

    String getAddress_streetname();

    void setAddress_streetname(String address_streetname);

    String getAddress_postcode();

    void setAddress_postcode(String address_postcode);

    String getRole();

    void setRole(String role);

    String getSecuritycredentials_username();

    void setSecuritycredentials_username(String securitycredentials_username);

    String getSecuritycedentials_password();

    void setSecuritycedentials_password(String securitycedentials_password);

    List<ManagerTeamJpaValueObject> getTeam();

    void setTeam(List<ManagerTeamJpaValueObject> team);

    void removeTeamMember(ManagerTeamJpaValueObject newTeamMember);
}
