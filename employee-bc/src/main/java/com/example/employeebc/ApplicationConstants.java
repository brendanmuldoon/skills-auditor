package com.example.employeebc;

public class ApplicationConstants {

    private ApplicationConstants(){}

    // Error messages
    public static final String POSTCODE_ERROR_MSG_EMPTY = "Postcode cannot be empty";
    public static final String STREET_NAME_ERROR_MSG_EMPTY = "Street name cannot be empty";
    public static final String HOUSE_NUMBER_ERROR_MSG_EMPTY = "House number cannot be empty";
    public static final String FIRST_NAME_ERROR_MSG_EMPTY = "First name cannot be empty";
    public static final String SURNAME_ERROR_MSG_EMPTY = "Surname cannot be empty";
    public static final String PASSWORD_ERROR_MSG_EMPTY = "Password cannot be empty";
    public static final String USERNAME_ERROR_MSG_EMPTY = "Username cannot be empty";
    public static final String SKILL_ID_ERROR_MSG_EMPTY = "SkillId cannot be empty";
    public static final String SKILL_EXPIRY_MONTH_ERROR_MSG = "'%s' is an invalid month";
    public static final String SKILL_EXPIRY_YEAR_ERROR_MSG = "'%' has already expired";
    public static final String STAFF_ID_NOT_RECOGNISED_ERROR_MSG = "Staff id is not recognised";
    public static final String MANAGER_ID_NOT_RECOGNISED_ERROR_MSG = "Manager id is not recognised";
    public static final String USER_NOT_AUTHORISED_ERROR_MSG = "User is not authorised";
    public static final String NOT_FOUND_ERROR_MSG = "Not found";
    public static final String NO_SKILL_ERROR_MSG = "Staff does not have that skill";
    public static final String ADMIN ="ADMIN";
    public static final String GET_ID_URL = "http://localhost:8082/id";
    public static final String VALIDATE_ROLE_URL = "http://localhost:8082/validateRole";
    public static final String FIND_ALL_SKILLS_BY_CATEGORY_ID = "http://localhost:8081/skill/findAllSkillsByCategory/%s";
    public static final String SKILL_CREATE_QUEUE = "SKILL.CREATE.QUEUE";
    public static final String SKILL_EDIT_QUEUE = "SKILL.EDIT.QUEUE";
    public static final String EMPLOYEE_DELETE_SKILL_QUEUE = "EMPLOYEE.DELETE.SKILL.QUEUE";
    public static final String CATEGORY_CREATE_QUEUE = "CATEGORY.CREATE.QUEUE";
    public static final String CATEGORY_EDIT_QUEUE = "CATEGORY.EDIT.QUEUE";
    public static final String SKILL_CATEGORY_DELETE_QUEUE = "SKILL.CATEGORY.DELETE.QUEUE";
    public static final String IDENTITY_UPDATE_QUEUE = "IDENTITY.UPDATE.QUEUE";
    public static final String SKILL_DELETE_QUEUE = "SKILL.DELETE.QUEUE";
    public static final String SKILL_DELETE_ERROR_MSG = "Cannot delete, Skill is in use";

}
