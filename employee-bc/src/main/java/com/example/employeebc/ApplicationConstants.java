package com.example.employeebc;

public class ApplicationConstants {

    private ApplicationConstants(){}

    // Error messages
    public static final String POSTCODE_ERROR_MSG_EMPTY = "Postcode cannot be empty";
    public static final String STREET_NAME_ERROR_MSG_EMPTY = "Street name cannot be empty";
    public static final String HOUSE_NUMBER_ERROR_MSG_EMPTY = "House number cannot be empty";
    public static final String FIRST_NAME_ERROR_MSG_EMPTY = "First name cannot be empty";
    public static final String FIRST_NAME_ERROR_MSG_REGEX = "First name is not in a valid format";
    public static final String SURNAME_ERROR_MSG_EMPTY = "Surname cannot be empty";
    public static final String SURNAME_ERROR_MSG_REGEX = "Surname is not in a valid format";
    public static final String PASSWORD_ERROR_MSG_EMPTY = "Password cannot be empty";
    public static final String USERNAME_ERROR_MSG_EMPTY = "Username cannot be empty";
    public static final String SKILL_ID_ERROR_MSG_EMPTY = "SkillId cannot be empty";
    public static final String SKILL_SOF_ERROR_MSG_EMPTY = "Strength of Skill cannot be empty";
    public static final String SKILL_EXPIRY_ERROR_MSG = "Skill has already expired";
    public static final String SKILL_EXPIRY_MONTH_ERROR_MSG = "'%s' is an invalid month";
    public static final String SKILL_EXPIRY_YEAR_ERROR_MSG = "'%' has already expired";





    // Regex patterns
    public static final String FULL_NAME_REGEX_PATTERN = "/^[a-z ,.'-]+$/i";

}
