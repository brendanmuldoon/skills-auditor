package com.example.employeebc.employee.domain.staff;

public enum StrengthOfSkill {

    BASIC("Basic"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");

    private final String strength;

    StrengthOfSkill(String strength) {
        this.strength = strength;
    }

    public String getStrength() {
        return strength;
    }


}
