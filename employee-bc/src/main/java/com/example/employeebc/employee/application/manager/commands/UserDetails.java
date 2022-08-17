package com.example.employeebc.employee.application.manager.commands;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDetails {
    private String id;
    private String token;
    private String username;

    public static UserDetails userDetailsOf(String id, String token, String username) {
        return new UserDetails(id, token, username);
    }
}
