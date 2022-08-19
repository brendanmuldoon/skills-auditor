package com.example.employeebc.employee.domain.common;

import static com.example.employeebc.ApplicationConstants.*;

public class FullName extends ValueObject {

    private String firstName;
    private String surname;

    public FullName(String firstName, String surname) {
        setFirstName(firstName);
        setSurname(surname);
    }

    public FullName(FullName fullName) {
        this(fullName.firstName, fullName.surname);
    }

    private void setSurname(String surname) {
        assertArgumentNotEmpty(surname, SURNAME_ERROR_MSG_EMPTY);
        this.surname = surname;
    }

    private void setFirstName(String firstName) {
        assertArgumentNotEmpty(firstName, FIRST_NAME_ERROR_MSG_EMPTY);
        this.firstName=firstName;
    }

    public String firstName() {
        return firstName;
    }

    public String surname() {
        return surname;
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        FullName fullName = (FullName) o;

        return fullName.firstName.equals(this.firstName) &&
                fullName.surname.equals(this.surname);
    }

}
