package com.example.identitybc.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDetails {
    private String id;
    private String username;
    private String password;
    private String token;
}
