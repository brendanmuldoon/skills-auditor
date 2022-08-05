package com.example.employeebc.employee.domain.common;

import static com.example.employeebc.ApplicationConstants.PASSWORD_ERROR_MSG_EMPTY;
import static com.example.employeebc.ApplicationConstants.USERNAME_ERROR_MSG_EMPTY;

public class SecurityCredentials extends ValueObject {

    private String username;

    private String password;

    public SecurityCredentials(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    private void setPassword(String password) {
        assertArgumentNotEmpty(password, PASSWORD_ERROR_MSG_EMPTY);
        this.password = password;
    }

    private void setUsername(String username) {
        assertArgumentNotEmpty(username, USERNAME_ERROR_MSG_EMPTY);
        this.username=username;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        SecurityCredentials securityCredentials = (SecurityCredentials) o;

        return securityCredentials.username.equals(this.username) &&
                securityCredentials.password.equals(this.password);
    }
}
