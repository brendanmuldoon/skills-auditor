package com.example.employeebc;

import com.example.employeebc.employee.application.manager.ManagerApplicationServiceTests;
import com.example.employeebc.employee.application.manager.ManagerQueryHandlerTests;
import com.example.employeebc.employee.application.manager.mappers.ManagerJpaToDTOMapperTests;
import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.domain.manager.DTO.ManagerDTOTests;
import com.example.employeebc.employee.domain.manager.DTO.ManagerTeamDTOTests;
import com.example.employeebc.employee.domain.manager.ManagerAggregateTests;
import com.example.employeebc.employee.domain.manager.ManagerTeamTests;
import com.example.employeebc.employee.domain.manager.ManagerToManagerJpaMapperTests;
import com.example.employeebc.employee.domain.staff.DTO.StaffDTOTests;
import com.example.employeebc.employee.domain.staff.DTO.StaffSkillDTOTests;
import com.example.employeebc.employee.domain.staff.ExpirationDateTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
        {
                AddressTests.class,
                EmployeeTests.class,
                FullNameTests.class,
                IdentityTests.class,
                ManagerAggregateTests.class,
                ManagerTeamTests.class,
                ManagerToManagerJpaMapperTests.class,
                ManagerDTOTests.class,
                EmployeeDTOTests.class,
                ManagerTeamDTOTests.class,
                StaffDTOTests.class,
                StaffSkillDTOTests.class,
                ExpirationDateTests.class,
                ManagerJpaToDTOMapperTests.class,
                ManagerApplicationServiceTests.class,
                ManagerQueryHandlerTests.class
        }
)
public class TestSuite {
}
