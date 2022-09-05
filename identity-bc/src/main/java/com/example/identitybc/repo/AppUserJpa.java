package com.example.identitybc.repo;


import com.example.identitybc.service.events.IdentityUpdateUsernameAndPasswordEvent;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "app_user")
@Table(name = "app_user")
@Getter
@Setter
public class AppUserJpa {
    @Id
    @Column(name="id")
    private String userUUID;

    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="role_id")
    private RoleJpa role;

    public String toString(){
        return String.format("%s, %s, %s, %s %s" , userUUID,
                userName, password, role);
    }

    public void update(IdentityUpdateUsernameAndPasswordEvent event) {
        this.userName = event.getUsername();
        this.password = event.getPassword();
    }
}
