package com.example.employeebc;

import com.example.employeebc.employee.domain.common.*;
import com.example.employeebc.employee.domain.manager.DTO.ManagerDTOTests;
import com.example.employeebc.employee.domain.manager.ManagerAggregateTests;
import com.example.employeebc.employee.domain.manager.ManagerTeamTests;
import com.example.employeebc.employee.domain.manager.ManagerToManagerJpaMapperTests;
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
                EmployeeDTOTests.class
        }
)
public class TestSuite {
}
