package com.example.employeebc.employee.ui.staff;

import com.example.employeebc.employee.application.manager.commands.UserDetails;
import com.example.employeebc.employee.application.staff.commands.AddStaffSkillCommand;
import com.example.employeebc.employee.application.staff.commands.RemoveStaffSkillCommand;
import com.example.employeebc.employee.application.staff.commands.UpdateStaffDetailsCommand;
import com.example.employeebc.employee.application.staff.commands.UpdateStaffSkillCommand;
import com.example.employeebc.employee.ui.manager.IIdentityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static com.example.employeebc.ApplicationConstants.USER_NOT_AUTHORISED_ERROR_MSG;

@RequestMapping("/staff")
@RestController
@AllArgsConstructor
public class StaffController {
    private IStaffApplicationService applicationService;
    private IIdentityService identityService;

    @PutMapping("/updateDetails")
    public void updateStaffDetails(@RequestBody UpdateStaffDetailsCommand updateStaffDetailsCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(updateStaffDetailsCommand.getUserId(), updateStaffDetailsCommand.getToken(), updateStaffDetailsCommand.getUsername());
        if(identityService.isAdmin(userDetails) || identityService.isSpecifiedUser(userDetails, updateStaffDetailsCommand.getUserId())) {
            applicationService.updateStaffDetails(updateStaffDetailsCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_NOT_AUTHORISED_ERROR_MSG);
        }
    }

    @PostMapping("/staffSkill/add")
    public void addStaffSkill(@RequestBody AddStaffSkillCommand addStaffSkillCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(addStaffSkillCommand.getUserId(), addStaffSkillCommand.getToken(), addStaffSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails) || identityService.isSpecifiedUser(userDetails, addStaffSkillCommand.getUserId())) {
            applicationService.addStaffSkill(addStaffSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_NOT_AUTHORISED_ERROR_MSG);
        }
    }

    @DeleteMapping("/staffSkill/removeSkill")
    public void removeStaffSkill(@RequestBody RemoveStaffSkillCommand removeStaffSkillCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(removeStaffSkillCommand.getUserId(), removeStaffSkillCommand.getToken(), removeStaffSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails) || identityService.isSpecifiedUser(userDetails, removeStaffSkillCommand.getUserId())) {
            applicationService.removeStaffSkill(removeStaffSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_NOT_AUTHORISED_ERROR_MSG);
        }
    }

    @PutMapping("/staffSkill/updateSkill")
    public void updateStaffSkill(@RequestBody UpdateStaffSkillCommand updateStaffSkillCommand) {
        UserDetails userDetails = UserDetails.userDetailsOf(updateStaffSkillCommand.getUserId(), updateStaffSkillCommand.getToken(), updateStaffSkillCommand.getUsername());
        if(identityService.isAdmin(userDetails) || identityService.isSpecifiedUser(userDetails, updateStaffSkillCommand.getUserId())) {
            applicationService.updateStaffSkill(updateStaffSkillCommand);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_NOT_AUTHORISED_ERROR_MSG);
        }
    }

}
