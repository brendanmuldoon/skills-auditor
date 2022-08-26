package com.example.employeebc.employee.application.identity;

import com.example.employeebc.employee.application.manager.commands.UserDetails;
import com.example.employeebc.employee.ui.manager.IIdentityService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IdentityService implements IIdentityService {

    public String validateAndGetRole(UserDetails command) {
        String URL = "http://localhost:8082/validateRole";
        return getResponseFromIdentityContext(command, URL);
    }

    public String getID(UserDetails command) {
        String URL = "http://localhost:8082/id";
        return getResponseFromIdentityContext(command, URL);
    }

    private String getResponseFromIdentityContext(UserDetails command, String URL){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<UserDetails> request = new HttpEntity<>(
                new UserDetails(command.getId(), command.getToken(), command.getUsername()));
        String response = restTemplate
                .postForObject(URL, request, String.class);
        return response;
    }

    public boolean isSpecifiedUser(UserDetails command, String employeeId){
        return getID(command).equals(employeeId);
    }


    public boolean isAdmin(UserDetails command){
        return validateAndGetRole(command).equals("ADMIN");
    }
}
