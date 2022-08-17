package com.example.employeebc.employee.ui.manager;

import com.example.employeebc.employee.application.manager.commands.*;
import com.example.employeebc.employee.application.manager.queries.EmployeeSkillDTOList;
import com.example.employeebc.employee.application.manager.queries.GetTeamBySkillIdQuery;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {

    private IManagerQueryHandler queryHandler;

    private IManagerApplicationService applicationService;

    private IIdentityService identityService;

    // find all managers
    @GetMapping("/findAll") // REMOVE
    public Iterable<?> getAllManagerDetails() {
        return queryHandler.findAll();
    }

    // find manager by id
    @GetMapping("{manager_id}") // REMOVE
    public Optional<?> getManagerById(@PathVariable(value = "manager_id") String managerId) {
        Optional<?> response = queryHandler.findByManagerId(managerId);
        if(response.isPresent()) {
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Manager id: '%s' not found", managerId));
        }
    }

    @GetMapping("/team/{manager_id}") // REMOVE
    public List<?> getManagerTeamById(@PathVariable(value = "manager_id") String managerId) {
        List<?> response = queryHandler.findTeamByManagerId(managerId);
        if(!response.isEmpty()) {
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Manager id: '%s' not found", managerId));
        }
    }

    @PostMapping("/team/bySkill/{skill_id}") // -- Tested: ok
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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not authorised");
    }

    @GetMapping("/team/expiredSkills")
    public void getAllStaffWithExpiredSkills() {
        // do nothing for now
    }

    @PostMapping("/team/addToTeam/{staff_id}") // -- Tested: ok
    public void updateManagerTeam(@PathVariable(value = "staff_id") String staffId,
                                  @RequestBody UserDetails command) {
        if(identityService.isAdmin(command)) {
            applicationService.addStaffToManagerTeam(command.getId(), staffId);
        }
    }

    @PostMapping("/createSkill") // -- Tested: ok
    public void createSkill(@RequestBody CreateSkillCommand createSkillCommand) {
        UserDetails userDetails = new UserDetails(createSkillCommand.getManagerId(), createSkillCommand.getToken(), createSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails)) {
            applicationService.createSkill(createSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not authorised");
        }
    }

    @PutMapping("/editSkill") // -- Tested: ok
    public void editSkill(@RequestBody EditSkillCommand editSkillCommand) {
        UserDetails userDetails = new UserDetails(editSkillCommand.getManagerId(), editSkillCommand.getToken(), editSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails)) {
            applicationService.editSkill(editSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not authorised");
        }
    }

    @DeleteMapping("/deleteSkill")
    public void deleteSkill(@RequestBody DeleteSkillCommand deleteSkillCommand) {
        applicationService.deleteSkill(deleteSkillCommand);
    }

    @GetMapping("/findAllSkillsByCategory/{category_id}")
    public EmployeeSkillDTOList getSkillsByCategoryId(@PathVariable(name = "category_id") String categoryId){
        EmployeeSkillDTOList response = queryHandler.findSkillsByCategory(categoryId);
        if(!response.getSkills().isEmpty()) {
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category id: '%s' not found", categoryId));
        }
    }

    @PostMapping("/createCategory")
    public void createCategory(@RequestBody CreateCategoryCommand createSkillCommand) {
        applicationService.createCategory(createSkillCommand);
    }

    // edit category
    @PutMapping("/editCategory")
    public void editCategory(@RequestBody EditCategoryCommand editCategoryCommand) {
        applicationService.editCategory(editCategoryCommand);
    }

    // delete category
    @DeleteMapping("/deleteCategory")
    public void deleteCategory(@RequestBody DeleteCategoryCommand deleteCategoryCommand) {
        applicationService.deleteCategory(deleteCategoryCommand);
    }

}
