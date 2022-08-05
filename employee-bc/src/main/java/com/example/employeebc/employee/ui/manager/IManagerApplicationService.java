package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.domain.manager.interfaces.*;

public interface IManagerApplicationService {
    void addStaffToManagerTeam(IUpdateManagerTeamCommand updateManagerTeamCommand);

    void createSkill(ICreateSkillCommand createSkillCommand);

    void editSkill(IEditSkillCommand editSkillCommand);

    void deleteSkill(IDeleteSkillCommand deleteSkillCommand);

    void createCategory(ICreateCategoryCommand createSkillCommand);

    void editCategory(IEditCategoryCommand editCategoryCommand);

    void deleteCategory(IDeleteCategoryCommand deleteCategoryCommand);
}
