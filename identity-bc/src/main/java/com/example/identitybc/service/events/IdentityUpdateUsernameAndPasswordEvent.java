package com.example.identitybc.service.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IdentityUpdateUsernameAndPasswordEvent {

    private String id;
    private String username;
    private String password;

}
