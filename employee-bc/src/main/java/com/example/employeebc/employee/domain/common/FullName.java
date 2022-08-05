package com.example.employeebc.employee.domain.common;

import static com.example.employeebc.ApplicationConstants.*;

public class FullName extends ValueObject {

    private String firstName;
    private String surname;

    public FullName(String firstName, String surname) {
        setFirstName(firstName);
        setSurname(surname);
    }

    private void setSurname(String surname) {
        assertArgumentNotEmpty(surname, SURNAME_ERROR_MSG_EMPTY);
//        assertMatchesRegex(surname, FULL_NAME_REGEX_PATTERN, SURNAME_ERROR_MSG_REGEX);
        this.surname = surname;
    }

    private void setFirstName(String firstName) {
        assertArgumentNotEmpty(firstName, FIRST_NAME_ERROR_MSG_EMPTY);
//        assertMatchesRegex(surname, FULL_NAME_REGEX_PATTERN, FIRST_NAME_ERROR_MSG_REGEX);
        this.firstName=firstName;
    }

    public String firstName() {
        return firstName;
    }

    public String surname() {
        return surname;
    }
}
