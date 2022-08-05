package com.example.employeebc.employee.ui.staff;

import com.example.employeebc.employee.application.staff.commands.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/staff")
@RestController
@AllArgsConstructor
public class StaffController {

    private IStaffQueryHandler queryHandler;
    private IStaffApplicationService applicationService;

    @GetMapping("/findAll") // REMOVE
    public Iterable<?> getAllStaffDetails() {
        return queryHandler.findAll();
    }

    @GetMapping("/{staffId}") // REMOVE
    public Optional<?> getStaffDetailsByStaffId(@PathVariable(value = "staffId") String staffId) {
        Optional<?> response = queryHandler.findByStaffId(staffId);
        if(response.isPresent()) {
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Staff id: '%s' not found", staffId));
        }
    }

    @PutMapping("/updateDetails")
    public void updateStaffDetails(@RequestBody UpdateStaffDetailsCommand updateStaffDetailsCommand) {
        applicationService.updateStaffDetails(updateStaffDetailsCommand);
    }

    @PostMapping("/createStaff") // REMOVE
    public void createStaff(@RequestBody CreateStaffCommand createStaffCommand) {
        applicationService.createStaff(createStaffCommand);
    }

    @DeleteMapping("/deleteStaff") // REMOVE
    public void deleteStaff(@RequestBody DeleteStaffCommand deleteStaffCommand) {
        applicationService.deleteStaff(deleteStaffCommand);
    }

    @GetMapping("staffSkill/{staffId}") // REMOVE
    public List<?> getStaffSkillsDetailsByStaffId(@PathVariable(value = "staffId") String staffId) {
        List<?> response = queryHandler.findSkillsByStaffId(staffId);
        if(!response.isEmpty()) {
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Staff id: '%s' not found", staffId));
        }
    }

    @PostMapping("/staffSkill/add")
    public void addStaffSkill(@RequestBody AddStaffSkillCommand addStaffSkillCommand) {
        applicationService.addStaffSkill(addStaffSkillCommand);
    }

    @DeleteMapping("/staffSkill/removeSkill")
    public void removeStaffSkill(@RequestBody RemoveStaffSkillCommand removeStaffSkillCommand) {
        applicationService.removeStaffSkill(removeStaffSkillCommand);
    }

    @PutMapping("/staffSkill/updateSkill")
    public void updateStaffSkill(@RequestBody UpdateStaffSkillCommand updateStaffSkillCommand) {
        applicationService.updateStaffSkill(updateStaffSkillCommand);
    }

}
