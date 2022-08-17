package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.domain.manager.interfaces.*;

public interface IManagerApplicationService {
    void addStaffToManagerTeam(String managerId, String staffId);

    void createSkill(ICreateSkillCommand createSkillCommand);

    void editSkill(IEditSkillCommand editSkillCommand);

    void deleteSkill(IDeleteSkillCommand deleteSkillCommand);

    void createCategory(ICreateCategoryCommand createSkillCommand);

    void editCategory(IEditCategoryCommand editCategoryCommand);

    void deleteCategory(IDeleteCategoryCommand deleteCategoryCommand);
}
