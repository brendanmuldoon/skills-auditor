package com.example.identitybc.repo;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Role")
@Table(name = "role")
@Getter
@Setter
public class RoleJpa {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "type")
    private String type;

    public String toString(){
        return type;
    }
}
