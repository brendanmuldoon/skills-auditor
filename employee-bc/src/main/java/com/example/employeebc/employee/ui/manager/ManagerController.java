package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.application.manager.commands.*;
import com.example.employeebc.employee.application.manager.queries.EmployeeSkillDTOList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {

    private IManagerQueryHandler queryHandler;

    private IManagerApplicationService applicationService;

    private IIdentityService identityService;

    @PostMapping("/team/findTeamByManagerId/{manager_id}")
    public List<?> getManagerTeam(@PathVariable(value = "manager_id") String managerId,
                                  @RequestBody UserDetails command) {
        if(identityService.isAdmin(command)) {
            List<?> response = queryHandler.findTeamByManagerId(managerId);
            if(!response.isEmpty()) {
                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorised");
    }

    @PostMapping("/team/bySkill/{skill_id}")
    public List<?> getManagerTeamBySkillId(@PathVariable(value = "skill_id") String skillId,
                                           @RequestBody UserDetails command) {
        if(identityService.isAdmin(command)) {
            List<?> response = queryHandler.findTeamBySkillId(command.getId(), skillId);
            if(!response.isEmpty()) {
                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorised");
    }

    @PostMapping("/team/expiredSkills")
    public List<?> getAllStaffWithExpiredSkills(@RequestBody UserDetails userDetails) {
        if(identityService.isAdmin(userDetails)) {
            List<?> response = queryHandler.findAllWithExpiredSkills();
            if(!response.isEmpty()) {
                return response;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorised");
    }

    @PostMapping("/team/addToTeam/{staff_id}")
    public void updateManagerTeam(@PathVariable(value = "staff_id") String staffId,
                                  @RequestBody UserDetails command) {
        if(identityService.isAdmin(command)) {
            applicationService.addStaffToManagerTeam(command.getId(), staffId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorised");
        }
    }

    @PostMapping("/createSkill")
    public void createSkill(@RequestBody CreateSkillCommand createSkillCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(createSkillCommand.getId(), createSkillCommand.getToken(), createSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails)) {
            applicationService.createSkill(createSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorised");
        }
    }

    @PutMapping("/editSkill")
    public void editSkill(@RequestBody EditSkillCommand editSkillCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(editSkillCommand.getId(), editSkillCommand.getToken(), editSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails)) {
            applicationService.editSkill(editSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorised");
        }
    }

    @DeleteMapping("/deleteSkill")
    public void deleteSkill(@RequestBody DeleteSkillCommand deleteSkillCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(deleteSkillCommand.getId(), deleteSkillCommand.getToken(), deleteSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails)) {
            applicationService.deleteSkill(deleteSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorised");
        }
    }

    @PostMapping("/findAllSkillsByCategory/{category_id}")
    public EmployeeSkillDTOList getSkillsByCategoryId(@PathVariable(name = "category_id") String categoryId,
                                                      @RequestBody UserDetails userDetails){
        if(identityService.isAdmin(userDetails)) {
            EmployeeSkillDTOList response = queryHandler.findSkillsByCategory(categoryId);
            if(!response.getSkills().isEmpty()) {
                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category id: '%s' not found", categoryId));
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorised");

    }

    @PostMapping("/createCategory")
    public void createCategory(@RequestBody CreateCategoryCommand createSkillCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(createSkillCommand.getId(), createSkillCommand.getToken(), createSkillCommand.getUsername());
        if (identityService.isAdmin(userDetails)) {
            applicationService.createCategory(createSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorised");
        }
    }

    // edit category
    @PutMapping("/editCategory")
    public void editCategory(@RequestBody EditCategoryCommand editCategoryCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(editCategoryCommand.getId(), editCategoryCommand.getToken(), editCategoryCommand.getUsername());
        if (identityService.isAdmin(userDetails)) {
            applicationService.editCategory(editCategoryCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorised");
        }
    }

    // delete category
    @DeleteMapping("/deleteCategory")
    public void deleteCategory(@RequestBody DeleteCategoryCommand deleteCategoryCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(deleteCategoryCommand.getId(), deleteCategoryCommand.getToken(), deleteCategoryCommand.getUsername());
        if(identityService.isAdmin(userDetails)) {
            applicationService.deleteCategory(deleteCategoryCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorised");
        }
    }

}
