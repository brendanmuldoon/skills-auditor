package com.example.employeebc.employee.domain.common;

import java.time.LocalDate;
import java.util.UUID;

public class AssertionConcern {

    protected AssertionConcern() {
        super();
    }

    protected void assertArgumentIsUUID(String id) throws IllegalArgumentException{
        UUID.fromString(id.trim());//throws IllegalArgumentException if invalid
    }

    protected void assertArgumentLength(String aString, int aMaximum, String aMessage) {
        int length = aString.trim().length();
        if (length > aMaximum) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertArgumentLength(String aString, int aMinimum, int aMaximum, String aMessage) {
        int length = aString.trim().length();
        if (length < aMinimum || length > aMaximum) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertArgumentNotEmpty(String aString, String aMessage) {
        if (aString == null || aString.trim().isEmpty()) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertDateIsBefore(LocalDate dateBeingChecked, LocalDate dateMustBeBefore, String aMessage) {
        if (dateBeingChecked.isBefore(dateMustBeBefore)) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertMatchesRegex(String aString, String aPattern, String aMessage) {
        if(!aString.matches(aPattern)) {
            throw new IllegalArgumentException(aMessage);
        }
    }
}

